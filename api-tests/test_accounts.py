import uuid

import pytest

from endpoints import Accounts, Customers


# ---------- create ----------

@pytest.mark.parametrize("account_type", ["CHECKING", "SAVINGS", "CREDIT"])
def test_create_account_all_types(api, make_customer, account_type):
    cid = make_customer()
    payload = {
        "type": account_type,
        "nickname": f"acct-{uuid.uuid4().hex[:6]}",
        "rewards": 0,
        "balance": 100.0,
    }
    response = api.post(Customers.accounts(cid), json=payload)
    assert response.status_code == 201, response.text
    assert response.json()["data"]["type"] == account_type


def test_create_account_fields(api, customer_id):
    payload = {
        "type": "CHECKING",
        "nickname": f"acct-{uuid.uuid4().hex[:6]}",
        "rewards": 0,
        "balance": 1000.0,
    }
    response = api.post(Customers.accounts(customer_id), json=payload)
    assert response.status_code == 201, response.text
    body = response.json()["data"]
    assert body["nickname"] == payload["nickname"]
    assert body["balance"] == 1000.0


def test_create_account_invalid_type(api, customer_id):
    payload = {"type": "BITCOIN", "nickname": "bad", "rewards": 0, "balance": 0.0}
    response = api.post(Customers.accounts(customer_id), json=payload)
    assert response.status_code == 400, response.text


def test_create_account_customer_not_found(api):
    payload = {"type": "SAVINGS", "nickname": "ghost", "rewards": 0, "balance": 0.0}
    response = api.post(Customers.accounts(999999), json=payload)
    assert response.status_code == 404, response.text


# ---------- get ----------

def test_get_account(api, make_account):
    _, aid = make_account()
    response = api.get(Accounts.by_id(aid))
    assert response.status_code == 200, response.text
    assert response.json()["data"]["id"] == aid


def test_get_account_not_found(api):
    assert api.get(Accounts.by_id(999999)).status_code == 404


def test_get_customer_by_account_id(api, make_account):
    cid, aid = make_account()
    response = api.get(Accounts.customer_by_account_id(aid))
    assert response.status_code == 200, response.text
    assert response.json()["data"]["id"] == cid


# ---------- delete ----------

def test_delete_account(api, make_account):
    _, aid = make_account(balance=0.0)
    response = api.delete(Accounts.by_id(aid))
    assert response.status_code == 204, response.text
    assert api.get(Accounts.by_id(aid)).status_code == 404


def test_delete_account_not_found(api):
    assert api.delete(Accounts.by_id(999999)).status_code == 404


# ---------- authorization ----------

def test_get_all_accounts_as_admin(api):
    assert api.get(Accounts.GET_ALL_ACCOUNTS).status_code == 200


def test_get_all_accounts_as_user_forbidden(user_api):
    assert user_api.get(Accounts.GET_ALL_ACCOUNTS).status_code == 403


def test_get_all_accounts_no_credentials(anon_api):
    assert anon_api.get(Accounts.GET_ALL_ACCOUNTS).status_code == 401


def test_delete_account_as_user_forbidden(api, user_api, make_account):
    _, aid = make_account()
    assert user_api.delete(Accounts.by_id(aid)).status_code == 403
    assert api.get(Accounts.by_id(aid)).status_code == 200


def test_delete_account_no_credentials(api, anon_api, make_account):
    _, aid = make_account()
    assert anon_api.delete(Accounts.by_id(aid)).status_code == 401
    assert api.get(Accounts.by_id(aid)).status_code == 200
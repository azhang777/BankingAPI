import uuid

from endpoints import Customers


# ---------- create ----------

def test_create_customer(api):
    payload = {
        "firstName": f"Test{uuid.uuid4().hex[:6]}",
        "lastName": "User",
        "address": [],
    }
    response = api.post(Customers.CREATE_CUSTOMER, json=payload)
    assert response.status_code == 201, response.text
    body = response.json()["data"]
    assert body["id"] is not None
    assert body["firstName"] == payload["firstName"]
    assert body["lastName"] == payload["lastName"]


def test_create_customer_missing_first_name(api):
    # Your service rejects a null firstName; confirm the status your handler returns.
    response = api.post(Customers.CREATE_CUSTOMER, json={"lastName": "User", "address": []})
    assert response.status_code == 422, response.text


# ---------- get ----------

def test_get_customer(api, customer_id):
    response = api.get(Customers.by_id(customer_id))
    assert response.status_code == 200, response.text
    assert response.json()["data"]["id"] == customer_id


def test_get_customer_not_found(api):
    response = api.get(Customers.by_id(999999))
    assert response.status_code == 404, response.text


def test_get_customer_invalid_id_format(api):
    # "abc" can't convert to Long, so Spring rejects it before your service runs.
    response = api.get(Customers.by_id("abc"))
    assert response.status_code == 400, response.text


# ---------- update ----------

def test_update_customer(api, make_customer):
    cid = make_customer()
    new_name = f"Upd{uuid.uuid4().hex[:6]}"
    response = api.put(Customers.by_id(cid), json={"firstName": new_name, "lastName": "Changed"})
    assert response.status_code == 202, response.text

    fetched = api.get(Customers.by_id(cid)).json()["data"]
    assert fetched["firstName"] == new_name
    assert fetched["lastName"] == "Changed"


def test_update_customer_partial(api, make_customer):
    cid = make_customer()
    original = api.get(Customers.by_id(cid)).json()["data"]
    response = api.put(Customers.by_id(cid), json={"lastName": "OnlyLast"})
    assert response.status_code == 202, response.text

    fetched = api.get(Customers.by_id(cid)).json()["data"]
    assert fetched["lastName"] == "OnlyLast"
    assert fetched["firstName"] == original["firstName"]


def test_update_customer_not_found(api):
    response = api.put(Customers.by_id(999999), json={"firstName": "Ghost"})
    assert response.status_code == 404, response.text


# ---------- delete ----------

def test_delete_customer(api, make_customer):
    cid = make_customer()
    response = api.delete(Customers.by_id(cid))
    assert response.status_code == 200, response.text
    assert response.json()["code"] == 200
    assert api.get(Customers.by_id(cid)).status_code == 404


def test_delete_customer_not_found(api):
    response = api.delete(Customers.by_id(999999))
    assert response.status_code == 404, response.text


def test_delete_customer_with_accounts_is_blocked(api, make_customer):
    cid = make_customer()
    api.post(Customers.accounts(cid), json={
        "type": "SAVINGS", "nickname": "keep", "rewards": 0, "balance": 0.0,
    })
    response = api.delete(Customers.by_id(cid))
    assert response.status_code == 409, response.text


# ---------- authorization ----------

def test_get_all_customers_as_admin(api):
    response = api.get(Customers.GET_ALL_CUSTOMERS)
    assert response.status_code == 200, response.text

def test_get_customer_as_user_forbidden(user_api, customer_id):
    assert user_api.get(Customers.by_id(customer_id)).status_code == 403

def test_get_all_customers_as_user_forbidden(user_api):
    assert user_api.get(Customers.GET_ALL_CUSTOMERS).status_code == 403


def test_get_all_customers_no_credentials(anon_api):
    assert anon_api.get(Customers.GET_ALL_CUSTOMERS).status_code == 401


def test_get_customer_no_credentials(anon_api):
    assert anon_api.get(Customers.by_id(1)).status_code == 401


def test_delete_customer_as_user_forbidden(api, user_api, make_customer):
    cid = make_customer()
    assert user_api.delete(Customers.by_id(cid)).status_code == 403
    assert api.get(Customers.by_id(cid)).status_code == 200   # still exists


def test_delete_customer_no_credentials(api, anon_api, make_customer):
    cid = make_customer()
    assert anon_api.delete(Customers.by_id(cid)).status_code == 401
    assert api.get(Customers.by_id(cid)).status_code == 200   # still exists
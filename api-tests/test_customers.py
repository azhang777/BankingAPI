import uuid

from endpoints import Customers

def test_create_customer(api):
    payload = {
        "firstName": f"Test{uuid.uuid4().hex[:6]}",
        "lastName": "User",
        "address": [],
    }
    response = api.post(Customers.CREATE_CUSTOMER, json=payload)
    print(response.status_code, response.text)
    assert response.status_code == 201

def test_get_customer(api, customer_id):
    response = api.get(Customers.by_id(customer_id))
    print(response.status_code, response.text)
    assert response.status_code == 200
    body = response.json()["data"]
    assert body["id"] == customer_id

def test_customer_not_found(api):
    response = api.get(Customers.by_id(999999))
    print(response.status_code, response.text)
    assert response.status_code == 404

def make_customer(api):
    payload = {"firstName": f"Test{uuid.uuid4().hex[:6]}", "lastName": "User", "address": []}
    r = api.post(Customers.CREATE_CUSTOMER, json=payload)
    assert r.status_code == 201, r.text
    return r.json()["data"]["id"]

def test_update_customer(api):
    cid = make_customer(api)   # never update the shared session customer
    new_name = f"Upd{uuid.uuid4().hex[:6]}"
    response = api.put(Customers.by_id(cid), json={"firstName": new_name, "lastName": "Changed"})
    print(response.status_code, response.text)
    assert response.status_code == 202

    fetched = api.get(Customers.by_id(cid)).json()["data"]
    assert fetched["firstName"] == new_name
    assert fetched["lastName"] == "Changed"


def test_update_customer_partial(api):
    cid = make_customer(api)
    original = api.get(Customers.by_id(cid)).json()["data"]
    response = api.put(Customers.by_id(cid), json={"lastName": "OnlyLast"})
    assert response.status_code == 202

    fetched = api.get(Customers.by_id(cid)).json()["data"]
    assert fetched["lastName"] == "OnlyLast"
    assert fetched["firstName"] == original["firstName"]   # untouched


def test_update_customer_not_found(api):
    response = api.put(Customers.by_id(999999), json={"firstName": "Ghost"})
    print(response.status_code, response.text)
    assert response.status_code == 404

def test_delete_customer(api):
    cid = make_customer(api)   # never delete the shared session customer
    response = api.delete(Customers.by_id(cid))
    print(response.status_code, response.text)
    assert response.status_code == 200
    assert response.json()["code"] == 200
    assert api.get(Customers.by_id(cid)).status_code == 404

def test_delete_customer_with_accounts_is_blocked(api):
    cid = make_customer(api)   # its own customer, so no shared state
    api.post(Customers.accounts(cid), json={
        "type": "SAVINGS", "nickname": "keep", "rewards": 0, "balance": 0.0})
    response = api.delete(Customers.by_id(cid))
    print(response.status_code, response.text)
    assert response.status_code == 409
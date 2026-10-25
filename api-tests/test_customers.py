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

def test_create_account(api, customer_id):
    payload = {
        "type": "CHECKING",
        "nickname": f"acct-{uuid.uuid4().hex[:6]}",
        "rewards": 0,
        "balance": 1000.0,
    }
    response = api.post(Customers.accounts(customer_id), json=payload)
    print(response.status_code, response.text)
    assert response.status_code == 201
    body = response.json()["data"]
    assert body["type"] == "CHECKING"
    assert body["nickname"] == payload["nickname"]
    assert body["balance"] == 1000.0
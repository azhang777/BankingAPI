import pytest
import requests
import uuid

from endpoints import Customers


BASE_URL = "http://localhost:8080"





class Api:
    def __init__(self, base_url):
        self.base_url = base_url
        self.session = requests.Session()
    def get(self, path, **kwargs):
        return self.session.get(self.base_url + path, **kwargs)
    def post(self, path, **kwargs):
        return self.session.post(self.base_url + path, **kwargs)
    def put(self, path, **kwargs):
        return self.session.put(self.base_url + path, **kwargs)
    def delete(self, path, **kwargs):
        return self.session.delete(self.base_url + path, **kwargs)
    
@pytest.fixture(scope="session")
def api():
    return Api(BASE_URL)

@pytest.fixture(scope="session")
def customer_id(api):
    payload = {
        "firstName": f"Test{uuid.uuid4().hex[:6]}",
        "lastName": "User",
        "address": [],
    }
    response = api.post(Customers.CREATE_CUSTOMER, json=payload)
    assert response.status_code == 201
    return response.json()["data"]["id"]
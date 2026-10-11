class Customers:
    # An account can only be created if the customer exists, so we need to create a customer first
    # A customer can have many accounts
    BASE = "/customers"
    CREATE_CUSTOMER = BASE
    #TODO Get all customers should have some authentication, only admin should run this, normal user should get 401
    GET_ALL_CUSTOMERS = BASE

    @staticmethod
    def by_id(customer_id):
        return f"{Customers.BASE}/{customer_id}"

    @staticmethod
    def accounts(customer_id):
        return f"{Customers.BASE}/{customer_id}/accounts"

class Accounts:
    BASE = "/accounts"
    #TODO Get all accounts should have some authentication, only admin should run this, normal user should get 401
    GET_ALL_ACCOUNTS = BASE

    @staticmethod
    def by_id(account_id):
        return f"{Accounts.BASE}/{account_id}"

    @staticmethod
    def customer_by_account_id(account_id):
        return f"{Accounts.BASE}/{account_id}/customer"

class Deposits:
   @staticmethod
   def get_all_deposits_by_account_id(account_id):
       return f"/customers/{account_id}/deposits"

    # get, put, delete by id
   @staticmethod
   def by_id(deposit_id):
       return f"/deposits/{deposit_id}"

   @staticmethod
   def create_deposit(account_id):
        return f"/customers/{account_id}/deposits"

class Withdrawals:
   # get all withdrawals by account id and create withdrawal by account id
    @staticmethod
    def by_account_id(account_id):
        return f"/accounts/{account_id}/withdrawals"
    
    # update, delete, get withdrawls by id
    @staticmethod
    def by_withdrawal_id(withdrawal_id):
        return f"/withdrawals/{withdrawal_id}"

    
class Bills:
   # delete update and get bill by id
   @staticmethod
   def by_bill_id(bill_id):
       return f"/bills/{bill_id}" 

   # create and get all bills by account id
   @staticmethod
   def by_account_id(account_id):
       return f"/accounts/{account_id}/bills"

   @staticmethod
   def get_bills_by_customer_id(customer_id):
       return f"/customers/{customer_id}/bills"

class P2P:
   @staticmethod
   def create_p2p_transfer(payer_account_id, payee_account_id):
       return f"/p2p/accounts/{payer_account_id}/{payee_account_id}"

   # update and delete p2p transfer by id
   @staticmethod
   def by_payer_id(payer_account_id):
       return f"/p2p/accounts/{payer_account_id}"

class Transactions:
    BASE = "/transactions"
    #TODO Get all transactions should have some authentication, only admin should run this, normal user should get 401
    GET_ALL_TRANSACTIONS = BASE
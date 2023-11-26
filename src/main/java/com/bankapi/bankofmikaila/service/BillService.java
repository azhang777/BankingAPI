package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Bill;
import com.bankapi.bankofmikaila.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    /**
     * @REVIEW - Love how you thinking with the bill repository and making the map
     * with id for key and the bill object for the value but there is an easier way
     *
     * @TODO - Auto wire your bill repository and the account repository looks like this
     *
     * @Autowired
     * private BillRepository billRepository
     *
     * @Autowired
     * private AccountRepository accountRepository;
     *
     * @Autowired
     * private CustomerRepository customerRepository;
     *
     *
     *
     * @TODO - must add 2 queries in the BillRepository
     *
     *
     * @TODO - next we to change the logic in all of the methods and add 1 more method
     *
     *
     * @TODO - we can focus the exception handlers next.
     */



    /**
     * @Delete  - line 42
     */
    private static final Map<Long, Bill> billRepository = new HashMap<>();

    /**
     * @Delete - line 47
     */
    private static long nextBillId;

    @Autowired
    private BillRepository billRepository;


    /**
     * @Change
     *
     * @Todo - must get all bills by account Id. Logic is similar to other services.
     *
     *
     *  public List<Bill> getAllBills(Long accountId) {
     *
     *          if(accountRepository.findById(accountId).isEmpty(){
     *              throw new BillsByAccountIdNotFound(“error fetching bills”)
     *          }
     *
     *         return billRepository.findBillsByAccountId(accountId)
     *     }
     *
     */
    public List<Bill> getAllBills() {



        return new ArrayList<>(billRepository.values());
    }

    /**
     *
     * @TODO change logic
     *
     *  public Bill getBillById(Long id) {
     *  var bill = billRepository.findById(id);
     *      if(bill.isEmpty()){
     *          throw new BillByIdNotFound(“error fetching bill with id: ” + id);
     *      }
     *
     *  return   bill.get();
     *     }
     * @return
     */
    public Bill getBillById(Long id) {
        return billRepository.get(id);

        return (List<Bill>) billRepository.findAll();
    }

    public Optional<Bill> getBillById(Long id) {
        return billRepository.findById(id);

    }

    /**
     * @TODO - need another method to get all bills associated with the given customerId
     *
     * public Bill getBillsByCID(Long customerId){
     * var customer = customerRepository.findById(customerId);
     * if(customer.isEmpty()){
     *     throw new BillsByCustomerIdNotFound("error fetching bills");
     * }
     *    return billRepository.findBillsByCustomertId(customerId);
     * }
     */

    /**
     *
     * @TODO - we must be able to create a bill by an accountId
     *         Looks like this.
     *
     *     public Bill createBill(Bill bill, Long accountId){
     *         Optional<Account> account = accountRepository.findById(accountId);
     *
     *         if( account.isEmpty()){
     *         throw new BillsByAccountIdNotFound("Error creating bill: Account not found");
     *         }
     *         bill.setAccount(account.get());
     *     return billRepository.save(bill);
     *     }
     */
    public Bill createBill(Bill bill) {

        return billRepository.save(bill);
    }


    /**
     * @Todo - updateBill should look like this inside
     *
     * Optional<Bill> existingBill = billRepository.findById(id);
     *
     * if(existingBill.isPresent()){
     *     Bill newBill = existingBill.get();
     *     newBill.setId(updatedBill.getId());
     *     newBill.setStatus(updatedBill.getStatus());
     *     newBill.setPayee(updatedBill.getPayee());
     *     newBill.setNickname(updatedBill.getNickname());
     *     newBill.setCreationDate(updatedBill.getCreationDate());
     *     newBill.setPaymentDate(updatedBill.getPaymentDate());
     *     newBill.setRecurringDate(updatedBill.getRecurringDate());
     *     newBill.setUpcomingPaymentDate(updatedBill.getUpcomingPaymentDate());
     *     newBill.setPaymentAmount(updatedBill.getPaymentAmount());
     *     newBill.setAccount(updatedBill.getAccount());
     *     newBill.setCustomer(updatedBill.getCustomer());
     *
     *     billRepository.save(newBill);
     *
     *
     * }else {
     *     throw new BillByIdNotFound("Bill ID does not exist");
     * }
     *
     *
     *
     *
     *
     *
     */

    public Bill updateBill(Long id, Bill updatedBill) {
        if (billRepository.containsKey(id)) {
            updatedBill.setId(id);
            billRepository.put(id, updatedBill);
            return updatedBill;

    public Optional<Bill> updateBill(Long id, Bill updatedBill) {
        Optional<Bill> existingBill = billRepository.findById(id);

        if (existingBill.isPresent()) {
            updatedBill.setId(existingBill.get().getId());
            return Optional.of(billRepository.save(updatedBill));

        } else {
            // Handle not found case, maybe throw an exception
            return Optional.empty();
        }
    }

    /**
     * @TODO - make these changes to the deletebill
     *
     *
     * public void deleteBill(Long id){
     *     if (id == null) {
     *         throw new BillsByIdNotFound(“This id does not exist in bills”);
     *     }
     *
     *     billRepository.deleteById(id);
     *
     * }
     */

    public boolean deleteBill(Long id) {
        if (billRepository.existsById(id)) {
            billRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Custom method to associate a bill with an account
    public Bill createBillForAccount(Account account, Bill bill) {

        bill.setAccount(account);

        return billRepository.save(bill);
    }

}

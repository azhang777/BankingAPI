package com.bankapi.bankofmikaila.response;


import org.springframework.stereotype.Component;

@Component
public class BillResponse {
    /**
     * @TODO - Autowire the bill service
     *
     * @Autowired
     * private BillService billService;
     *
     * @Todo - create 6 methods that all return a response entity for each method in the bill service
     *
     * @Method1
     *
     * public ResponseEntity<?> getAllBills(Long accountId){
     *     Detail detail = new Detail();
     *     detail.setData(billService.getAllBills(accountId);
     *     detail.setCode(HttpStatus.OK.value());
     *
     *     return new ResponseEntity<>(detail, HttpStatus.OK);
     * }
     *
     * @Method2
     * public ResponseEntity<?> getBillById(Long billId){
     *           Detail detail = new Detail();
     *           detail.setData(billService.getAllBills(billId);
     *          detail.setCode(HttpStatus.OK.value());
     *
     *           return new ResponseEntity<>(detail, HttpStatus.OK);
     *       }
     *
     *
     * @Method3
     *    public ResponseEntity<?> getBillsByCID(Long customerId){
     *                 Detail detail = new Detail();
     *                detail.setData(billService.getBillsByCID(billId);
     *               detail.setCode(HttpStatus.OK.value());
     *
     *                return new ResponseEntity<>(detail, HttpStatus.OK);
     *           }
     *
     *
     * @Method4
     *     public ResponseEntity<?> createBill(Bill bill, Long accountId){
     *                      Detail detail = new Detail();
     *                    detail.setData(billService.getBillsByCID(bill,accountId);
     *                   detail.setCode(HttpStatus.OK.value());
     *                   detail.setMessage("Created bill and added it to the account")
     *
     *                      return new ResponseEntity<>(detail, HttpStatus.OK);
     *                }
     *
     * @Method5
     *       public ResponseEntity<?> updateBill(Long billId, Bill bill ){
     *                           Detail detail = new Detail();
     *                       detail.setMessage("Accepted bill modification");
     *
     *                           return new ResponseEntity<>(detail, HttpStatus.ACCEPTED);
     *                    }
     * @Method6
     *  public ResponseEntity<?> deleteBill(Long billId){
     *
     *
     *             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     *
     *      }
     *
     */
}

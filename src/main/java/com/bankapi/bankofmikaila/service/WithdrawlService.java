package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.exception.DepositByIdNotFound;
import com.bankapi.bankofmikaila.exception.WithdrawalByIdNotFound;
import com.bankapi.bankofmikaila.exception.WithdrawlsByAccountNotFound;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Withdrawl;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.WithdrawRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WithdrawlService {

    @Autowired
    private WithdrawRepo withdrawRepo;

    @Autowired
    private AccountRepository accountRepository;



    protected void verifyAccount(Long aid) throws WithdrawlsByAccountNotFound {
        Optional<Account> account = accountRepository.findById(aid);

        if(account == null){
            throw new WithdrawlsByAccountNotFound("Account not found");
        }
    } protected void verifyWithdrawal(Long wid) throws WithdrawalByIdNotFound {
        Optional<Withdrawl> withdrawal = withdrawRepo.findById(wid);

        if(withdrawal == null){
            throw new WithdrawalByIdNotFound("error fetching withdrawal with id: " + wid );
        }
    }

    public Iterable<Withdrawl> getAllWithdrawlsByAID(Long accountId){
        verifyAccount(accountId);
      return   withdrawRepo.findWithdrawlsByAccountId(accountId);
    }



    public Withdrawl getWithdrawlById(Long withdrawlId){
        verifyWithdrawal(withdrawlId);
        return withdrawRepo.findById(withdrawlId).get();
    }


    public Withdrawl createWithdrawl(Withdrawl withdrawl, Long accountId){
        var account = accountRepository.findById(accountId);

        if(account.isEmpty()){
            throw  new WithdrawlsByAccountNotFound("Error creating withdrawal: Account not found");
        }
        withdrawl.setAccount(account.get());
    return withdrawRepo.save(withdrawl);
    }

  public void updateWithdrawl(Withdrawl withdrawl, Long withdrawlId){

      var xWithdrawalOp = withdrawRepo.findById(withdrawlId);


      if(xWithdrawalOp.isPresent()){
        var xWithdrawal = xWithdrawalOp.get();
          xWithdrawal.setAmount(withdrawl.getAmount());
          xWithdrawal.setMedium(withdrawl.getMedium());
          xWithdrawal.setDescription(withdrawl.getDescription());
          xWithdrawal.setAccount(withdrawl.getAccount());
          xWithdrawal.setTransactionDate(withdrawl.getTransactionDate());
          xWithdrawal.setStatus(withdrawl.getStatus());
          xWithdrawal.setType(withdrawl.getType());
          withdrawRepo.save(xWithdrawal);

      }else {
          throw new WithdrawalByIdNotFound("Withdrawal Id does not exist");
      }






  }

    public void deleteWithdrawals(Long id) {
        if(id == null){
            throw new WithdrawalByIdNotFound("This id does not exist in withdrawals");
        }
        withdrawRepo.deleteById(id);

    }


}

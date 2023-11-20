package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.exceptions.WithdrawalByIdNotFound;
import com.bankapi.bankofmikaila.exceptions.WithdrawlsByAccountNotFound;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Withdrawl;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.WithdrawRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    return withdrawRepo.save(withdrawl);
    }

  public void updateWithdrawl(Withdrawl withdrawl, Long withdrawlId){
      if(withdrawlId == null){
          throw new WithdrawalByIdNotFound("Withdrawal ID does not exist");
      }
    var xWithdrawal = withdrawRepo.findById(withdrawlId).get();
    xWithdrawal.setAmount(withdrawl.getAmount());
    xWithdrawal.setMedium(withdrawl.getMedium());
    xWithdrawal.setDescription(withdrawl.getDescription());
    xWithdrawal.setAccount(withdrawl.getAccount());
    xWithdrawal.setTransaction_date(withdrawl.getTransaction_date());
    xWithdrawal.setStatus(withdrawl.getStatus());
    xWithdrawal.setType(withdrawl.getType());


    withdrawRepo.save(xWithdrawal);


  }

    public void deleteWithdrawals(Long id) {
        if(id == null){
            throw new WithdrawalByIdNotFound("This id does not exist in withdrawals");
        }
        withdrawRepo.deleteById(id);

    }


}

package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.exception.DepositByAccountNotFound;
import com.bankapi.bankofmikaila.exception.DepositByIdNotFound;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Deposit;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private AccountRepository accountRepository;


    protected void verifyAccount(Long accountId) throws DepositByAccountNotFound {

        accountRepository.findById(accountId).orElseThrow(()->
                new DepositByAccountNotFound("Account not found."));

    }

    protected void verifyDeposit(Long depositId) throws DepositByIdNotFound {

        depositRepository.findById(depositId).orElseThrow(()->
                new DepositByIdNotFound("Error fetching deposit with id."));
    }


    public Iterable<Deposit> getAllDeposits(Long accountId){

        verifyAccount(accountId);

        return depositRepository.findDepositsByAccountId(accountId);

    }

    public Optional<Deposit> getDeposit(Long depositId){

        verifyDeposit(depositId);

        return depositRepository.findById(depositId);

    }

    public void createDeposit(Deposit deposit, Long accountId) {

        //uses lambda expression to check if account exists (by id), if not throw exception
        Account account = accountRepository.findById(accountId).orElseThrow(()->
                new DepositByAccountNotFound("Error creating deposit: account not found."));
        deposit.setAccount(account);
        //Save deposit once null check passes
        depositRepository.save(deposit);

//        if (accountRepository.findById(accountId).isPresent()) {
//            deposit = depositRepository.save(deposit);
//        } throw new EntityNotFoundException("Account with Id: " + accountId + " not found.");
    }

    public void updateDeposit(Deposit deposit, Long depositId){

        Optional<Deposit> existingDepositOptional = depositRepository.findById(depositId);

        if(existingDepositOptional.isPresent()){

            Deposit existingDeposit = existingDepositOptional.get();
            existingDeposit.setAmount(deposit.getAmount());
            existingDeposit.setMedium(deposit.getMedium());
            existingDeposit.setDescription(deposit.getDescription());
            existingDeposit.setPayee_id(deposit.getPayee_id());
            existingDeposit.setTransaction_date(deposit.getTransaction_date());
            existingDeposit.setStatus(deposit.getStatus());
            existingDeposit.setType(deposit.getType());

            depositRepository.save(existingDeposit);
        } else {
            throw new DepositByIdNotFound("Deposit ID does not exist.");
        }

    }

    public void deleteDeposit(Long depositId){

        Optional<Deposit> existingDepositOptional = depositRepository.findById(depositId);

        if(existingDepositOptional.isPresent()) {
            depositRepository.deleteById(depositId);
        } else {
            throw new DepositByIdNotFound("This id does not exist in deposits");
        }

    }



}

package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.model.Deposit;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private AccountRepository accountRepository;

    public Iterable<Deposit> getAllDeposits(){

        return depositRepository.findAll();

    }

    public Optional<Deposit> getDeposit(Long depositId){

        return depositRepository.findById(depositId);

    }

    public void createDeposit(Deposit deposit, Long accountId) {

        //uses lambda expression to check if account exists (by id), if not throw exception
        accountRepository.findById(accountId).orElseThrow(()->
                new EntityNotFoundException("Account with Id: " + accountId + " not found."));

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
            existingDeposit.setAccount(deposit.getAccount());
            existingDeposit.setTransactionDate(deposit.getTransactionDate());
            existingDeposit.setStatus(deposit.getStatus());
            existingDeposit.setType(deposit.getType());

            depositRepository.save(existingDeposit);
        } else {
            throw new EntityNotFoundException("Deposit with Id: " + depositId + " not found.");
        }

    }

    public void deleteDeposit(Long depositId){

        depositRepository.deleteById(depositId);

    }



}

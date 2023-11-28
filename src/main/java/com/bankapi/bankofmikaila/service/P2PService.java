package com.bankapi.bankofmikaila.service;

//@Service
//public class P2PService {
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Autowired
//    private WithdrawRepo withdrawRepo;
//
//
//    @Autowired
//    private TransactionRepository transactionRepository;
//    @Autowired
//    private TransactionFactory transactionFactory;
//
//    @Autowired
//    private TransactionService transactionService;
//
//    @Autowired
//    private AccountService accountService;
//
//    public P2P createP2P(Long payerId, Long payeeId, P2P p2p) {
//        Account payer = accountService.getAccountById(payerId);
//        Account payee = accountService.getAccountById(payeeId);
//
//        p2p.setAccount(payer);
//        p2p.setAccount2(payee);
//        Withdrawal withdrawal = new Withdrawal();
//        Deposit deposit = new Deposit();
//        withdrawal.setAccount(payer);
//        withdrawal.setAmount(p2p.getAmount());
//        withdrawal.setDescription(p2p.getDescription());
//        withdrawal.setType(TransactionType.P2P_WITHDRAWAL);
//        withdrawal.setMedium(TransactionMedium.BALANCE);
//        withdrawal.setStatus(TransactionStatus.PENDING);
//        withdrawal.setTransactionDate(p2p.getTransactionDate());
//        p2p.setWithdrawl(withdrawal);
//
//        deposit.setAccount(payee);
//        deposit.setAmount(p2p.getAmount());
//        deposit.setDescription(p2p.getDescription());
//        deposit.setType(TransactionType.P2P_DEPOSIT);
//        deposit.setMedium(TransactionMedium.BALANCE);
//        deposit.setStatus(TransactionStatus.PENDING);
//        deposit.setTransactionDate(p2p.getTransactionDate());
//        p2p.setDeposit(deposit);
//
//        transactionService.createWithdrawl(withdrawal, payer.getId());
//        transactionService.createDeposit(deposit, payee.getId());
//
//        return transactionRepository.save(p2p);
//    }
//
//
//
//
//
//}

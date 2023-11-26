package com.bankapi.bankofmikaila.model;

import javax.persistence.*;



@Entity
@Table(name= "Bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long id;


    @Column(name = "status")
    private String status;

    @Column(name = "payee")
    private String payee;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "creationDate")
    private String creationDate;

    @Column(name = "paymentDate")
    private String paymentDate;

    @Column(name = "recurringDate")
    private Integer recurringDate;

    @Column(name = "upcomingPaymentDate")
    private String upcomingPaymentDate;

    @Column(name = "paymentAmount")
    private Double paymentAmount;


    /**
     *
     *
     * @Todo - add two more things in here and generate getters and setters for them
     *
     * @JoinColumn(name = "account_id")
     * @ManyToOne
     * private Account account;
     *
     *   @JoinColumn(name = "customer_id")
     *     @ManyToOne
     * private Customer customer
     */

    /**
     * @Delete
     */
    @Column(name = "accountId")
    private Long accountId;

    //@TODO - Make this a nullable constructor.



    public Bill(Long id, String status, String payee, String nickname, String creationDate, String paymentDate, Integer recurringDate, String upcomingPaymentDate, Double paymentAmount, Long accountId) {
        this.id = id;
        this.status = status;
        this.payee = payee;
        this.nickname = nickname;
        this.creationDate = creationDate;
        this.paymentDate = paymentDate;
        this.recurringDate = recurringDate;
        this.upcomingPaymentDate = upcomingPaymentDate;
        this.paymentAmount = paymentAmount;
        this.accountId = accountId;


        @ManyToOne
        @JoinColumn(name = "account_id")
        private Account account;



        public Long getId () {
            return id;
        }

        public void setId (Long id){
            this.id = id;
        }

        public String getStatus () {
            return status;
        }

        public void setStatus (String status){
            this.status = status;
        }

        public String getPayee () {
            return payee;
        }

        public void setPayee (String payee){
            this.payee = payee;
        }

        public String getNickname () {
            return nickname;
        }

        public void setNickname (String nickname){
            this.nickname = nickname;
        }

        public String getCreationDate () {
            return creationDate;
        }

        public void setCreationDate (String creationDate){
            this.creationDate = creationDate;
        }

        public String getPaymentDate () {
            return paymentDate;
        }

        public void setPaymentDate (String paymentDate){
            this.paymentDate = paymentDate;
        }

        public Integer getRecurringDate () {
            return recurringDate;
        }

        public void setRecurringDate (Integer recurringDate){
            this.recurringDate = recurringDate;
        }

        public String getUpcomingPaymentDate () {
            return upcomingPaymentDate;
        }

        public void setUpcomingPaymentDate (String upcomingPaymentDate){
            this.upcomingPaymentDate = upcomingPaymentDate;
        }

        public Double getPaymentAmount () {
            return paymentAmount;
        }

        public void setPaymentAmount (Double paymentAmount){
            this.paymentAmount = paymentAmount;
        }

        public Account getAccount () {
            return account;
        }

        public void setAccount (Account account){
            this.account = account;
        }

    public
        Bill(Long id, String status, String payee, String nickname, String creationDate, String paymentDate, Integer recurringDate, String upcomingPaymentDate, Double paymentAmount, Long accountId)
        {
            this.id = id;
            this.status = status;
            this.payee = payee;
            this.nickname = nickname;
            this.creationDate = creationDate;
            this.paymentDate = paymentDate;
            this.recurringDate = recurringDate;
            this.upcomingPaymentDate = upcomingPaymentDate;
            this.paymentAmount = paymentAmount;
            this.account = account;
        }


        public enum BillStatus {
            PENDING,
            CANCELLED,
            COMPLETED,
            RECURRING
        }

    }
}


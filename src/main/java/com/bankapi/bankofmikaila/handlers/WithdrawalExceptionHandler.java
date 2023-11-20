package com.bankapi.bankofmikaila.handlers;

import com.bankapi.bankofmikaila.dto.ErrorDetail;
import com.bankapi.bankofmikaila.exceptions.WithdrawalByIdNotFound;
import com.bankapi.bankofmikaila.exceptions.WithdrawlsByAccountNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class WithdrawalExceptionHandler extends ResponseEntityExceptionHandler {



 @ExceptionHandler(WithdrawlsByAccountNotFound.class)
    public ResponseEntity<?> handleWithdrawalByAidNotFound(WithdrawlsByAccountNotFound withdrawlsByAccountNotFound){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(HttpStatus.NOT_FOUND.value());
        errorDetail.setMessage(withdrawlsByAccountNotFound.getMessage());

        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(WithdrawalByIdNotFound.class)
    public ResponseEntity<?> handleWithdrawalByIdNotFound(WithdrawalByIdNotFound withdrawalByIdNotFound){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(HttpStatus.NOT_FOUND.value());
        errorDetail.setMessage(withdrawalByIdNotFound.getMessage());

        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

}

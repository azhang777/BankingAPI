package com.bankapi.bankofmikaila.handlers;

import com.bankapi.bankofmikaila.dto.ErrorDetail;
import com.bankapi.bankofmikaila.exceptions.DepositByAccountNotFound;
import com.bankapi.bankofmikaila.exceptions.DepositByIdNotFound;
import com.bankapi.bankofmikaila.exceptions.WithdrawalByIdNotFound;
import com.bankapi.bankofmikaila.exceptions.WithdrawlsByAccountNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DepositExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(DepositByAccountNotFound.class)
    public ResponseEntity<?> handleDepositByAccountNotFound(DepositByAccountNotFound depositByAccountNotFound){

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(HttpStatus.NOT_FOUND.value());
        errorDetail.setMessage(depositByAccountNotFound.getMessage());

        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DepositByIdNotFound.class)
    public ResponseEntity<?> handleDepositByIdNotFound(DepositByIdNotFound depositByIdNotFound){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(HttpStatus.NOT_FOUND.value());
        errorDetail.setMessage(depositByIdNotFound.getMessage());

        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

}
package com.bankapi.bankofmikaila.handler;

import com.bankapi.bankofmikaila.dto.Detail;
import com.bankapi.bankofmikaila.dto.ErrorDetail;
import com.bankapi.bankofmikaila.dto.ErrorDetailAlt;
import com.bankapi.bankofmikaila.dto.ValidationError;
import com.bankapi.bankofmikaila.exception.*;
import com.bankapi.bankofmikaila.exception.AccountsNotFoundException;
import com.bankapi.bankofmikaila.exception.CustomersNotFoundException;
import com.bankapi.bankofmikaila.exception.InvalidTypeException;
import com.bankapi.bankofmikaila.exception.BillByIdNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;


    @ExceptionHandler(BillByIdNotFound.class)
    public ResponseEntity<?> handleBillByIdNotFoundException(BillByIdNotFound bnfe) {
        Detail detail = new Detail();
        detail.setCode(HttpStatus.NOT_FOUND.value());
        detail.setMessage(bnfe.getMessage());

        return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(WithdrawalByIdNotFound.class)
    public ResponseEntity<?> handleWithdrawalByIdNotFound(WithdrawalByIdNotFound withdrawalByIdNotFound) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(HttpStatus.NOT_FOUND.value());
        errorDetail.setMessage(withdrawalByIdNotFound.getMessage());

        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WithdrawalsByAccountNotFound.class)
    public ResponseEntity<?> handleWithdrawalByAidNotFound(WithdrawalsByAccountNotFound withdrawlsByAccountNotFound) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(HttpStatus.NOT_FOUND.value());
        errorDetail.setMessage(withdrawlsByAccountNotFound.getMessage());

        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(CustomersNotFoundException.class)
    public ResponseEntity<?> handleCustomerNotFoundException(CustomersNotFoundException cnfe) {
        Detail detail = new Detail();
        detail.setCode(HttpStatus.NOT_FOUND.value());
        detail.setMessage(cnfe.getMessage());

        return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountsNotFoundException.class)
    public ResponseEntity<?> handleAccountsNotFoundException(AccountsNotFoundException anfe) {
        Detail detail = new Detail();
        detail.setCode(HttpStatus.NOT_FOUND.value());
        detail.setMessage(anfe.getMessage());

        return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTypeException.class)
    public ResponseEntity<?> handleInvalidTypeException(InvalidTypeException ite) {
        Detail detail = new Detail();
        detail.setCode(HttpStatus.NOT_ACCEPTABLE.value());
        detail.setMessage(ite.getMessage());

        return new ResponseEntity<>(detail, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(BillsByAccountIdNotFoundException.class)
    public ResponseEntity<?> handleBillsByAccountIdNotFoundException(BillsByAccountIdNotFoundException bnfe) {
        Detail detail = new Detail();
        detail.setCode(HttpStatus.NOT_FOUND.value());
        detail.setMessage(bnfe.getMessage());

        return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BillsByCustomerIdNotFoundException.class)
    public ResponseEntity<?> handleBillsByCustomerIdNotFoundException(BillsByCustomerIdNotFoundException bnfe) {
        Detail detail = new Detail();
        detail.setCode(HttpStatus.NOT_FOUND.value());
        detail.setMessage(bnfe.getMessage());

        return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DepositByAccountNotFound.class)
    public ResponseEntity<?> handleDepositByAccountNotFound(DepositByAccountNotFound depositByAccountNotFound) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(HttpStatus.NOT_FOUND.value());
        errorDetail.setMessage(depositByAccountNotFound.getMessage());

        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DepositByIdNotFound.class)
    public ResponseEntity<?> handleDepositByIdNotFound(DepositByIdNotFound depositByIdNotFound) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(HttpStatus.NOT_FOUND.value());
        errorDetail.setMessage(depositByIdNotFound.getMessage());

        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetailAlt errorDetail = new ErrorDetailAlt();
        errorDetail.setTitle("Message Not Readable");
        errorDetail.setStatus(status.value());
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setTimeStamp(Calendar.getInstance().getTime());
        errorDetail.setDeveloperMessage(ex.getClass().getName());

        return handleExceptionInternal(ex, errorDetail, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetailAlt errorDetail = new ErrorDetailAlt();
        errorDetail.setTitle("Validation Failed");
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
//        String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");
//        if(requestPath == null) {
//            requestPath = request.getRequestURI();
//        } what is this doing here? need an explanation
        errorDetail.setDetail("Input validation failed");
        errorDetail.setTimeStamp(Calendar.getInstance().getTime());
        errorDetail.setDeveloperMessage(ex.getClass().getName());
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors(); //retrieve information from suchException to use for our body (field errors include things such as notEmpty and size (2,6)
        for (FieldError fe : fieldErrors) {
            List<ValidationError> validationErrorsList = errorDetail.getErrors().get(fe.getField()); //check if this error is within our collection
            if (validationErrorsList == null) { //if not there, create a new key value for it in the collection. String -> List<ValidationError)
                validationErrorsList = new ArrayList<>();
                errorDetail.getErrors().put(fe.getField(), validationErrorsList);
            }
            ValidationError validationError = new ValidationError();
            validationError.setCode(fe.getCode()); //"code": "NotEmpty",
            validationError.setMessage(messageSource.getMessage(fe, null)); //"message": "must not be empty"  *build the validation error from the current fieldError*
            validationErrorsList.add(validationError); //add it to the arrayList, which is stored in the hashmap errorDetail.errors
        }

        return handleExceptionInternal(ex, errorDetail, headers, status, request); //what is handleExceptionInternal? Seems like a method used to return a body for any exception handling?
    }


}


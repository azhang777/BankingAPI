package com.bankapi.bankofmikaila.handler;

//@ControllerAdvice
//public class DepositExceptionHandler extends ResponseEntityExceptionHandler {
//
//
//
//    @ExceptionHandler(DepositByAccountNotFound.class)
//    public ResponseEntity<?> handleDepositByAccountNotFound(DepositByAccountNotFound depositByAccountNotFound){
//
//        ErrorDetail errorDetail = new ErrorDetail();
//        errorDetail.setCode(HttpStatus.NOT_FOUND.value());
//        errorDetail.setMessage(depositByAccountNotFound.getMessage());
//
//        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(DepositByIdNotFound.class)
//    public ResponseEntity<?> handleDepositByIdNotFound(DepositByIdNotFound depositByIdNotFound){
//        ErrorDetail errorDetail = new ErrorDetail();
//        errorDetail.setCode(HttpStatus.NOT_FOUND.value());
//        errorDetail.setMessage(depositByIdNotFound.getMessage());
//
//        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
//    }
//
//
// }
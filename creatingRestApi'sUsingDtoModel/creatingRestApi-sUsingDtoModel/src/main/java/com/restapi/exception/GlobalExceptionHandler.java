package com.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request)
    {
        ErrorDetails errorDeatils=new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorDeatils, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    //handling custom validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> customValidation(MethodArgumentNotValidException exception)
     {
         ErrorDetails errorDeatils1=new ErrorDetails(new Date(),"Validation Error",exception.getBindingResult().getFieldError().getDefaultMessage());
         return new ResponseEntity<>(errorDeatils1,HttpStatus.BAD_REQUEST);
     }
//     @ExceptionHandler(MethodArgumentNotValidException.class)
//     public ResponseEntity<Map<String,String>>  handleMethodArgsNoValidationException(MethodArgumentNotValidException ex)
//     {
//         Map<String,String> resp=new HashMap<>();
//         ex.getBindingResult().getAllErrors().forEach((error )->{
//             String fieldName=((FieldError)error).getField();
//             String messagee= error.getDefaultMessage();
//             resp.put(fieldName,messagee);
//
//     });
//         return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
//     }

}

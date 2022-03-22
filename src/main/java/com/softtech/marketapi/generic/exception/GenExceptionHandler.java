package com.softtech.marketapi.generic.exception;

import com.softtech.marketapi.generic.dto.RestResponse;
import com.softtech.marketapi.generic.exceptions.GenericBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class GenExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public final ResponseEntity<Object> handleAllGenBusinessException(GenericBusinessException ex, WebRequest webRequest){

        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String description = webRequest.getDescription(false);

        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate, message, description);

        RestResponse<GenExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessages(message);

        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
    }
}

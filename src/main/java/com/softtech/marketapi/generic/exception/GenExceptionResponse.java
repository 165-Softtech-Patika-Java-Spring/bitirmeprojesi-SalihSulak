package com.softtech.marketapi.generic.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class GenExceptionResponse {
    private Date errorDate;
    private String message;
    private String detail;
}

package com.softtech.marketapi.generic.exceptions;

import com.softtech.marketapi.generic.enums.BaseErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class GenericBusinessException extends RuntimeException{

    private final BaseErrorMessage baseErrorMessage;
}

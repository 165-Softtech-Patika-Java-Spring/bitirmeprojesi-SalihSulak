package com.softtech.marketapi.security.enums;

public enum JwtConstant {
    BEARER("Bearer ");

    private String constant;
    JwtConstant(String constant) {
        this.constant = constant;
    }
    public String getConstant(){
        return constant;
    }


    @Override
    public String toString() {
        return constant;
    }
}

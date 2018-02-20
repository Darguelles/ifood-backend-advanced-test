package com.ifood.model.enums;

public enum Measure {

    CELCIUS("metric"),
    FAHRENHEIT("imperial");

    private String code;

    Measure(String code) {
        this.code = code;
    }

    public String code(){
        return code;
    }
}

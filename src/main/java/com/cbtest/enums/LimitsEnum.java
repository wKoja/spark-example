package com.cbtest.enums;

public enum LimitsEnum {

    MAX_AGE(100),
    MAX_CPF_SIZE_NOT_MASKED(11),
    MAX_CPF_SIZE_MASKED(14);

    private final long value;

    private LimitsEnum(long value){
        this.value = value;
    }

    public long value() {
        return value;
    }
}

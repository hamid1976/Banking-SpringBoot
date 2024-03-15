package com.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.micrometer.common.lang.Nullable;

@JsonSerialize(using = StatusEnumSerializer.class)
public enum StatusEnum {

    SUCCESS(0,  "Success"),
    FAILURE(1, "Error or Exception Occurred.");


    private int value;

    private final String reasonPhrase;

    StatusEnum(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }
    StatusEnum(Object value, String reasonPhrase) {
        if(value instanceof Integer){
            this.value = (int) value;
        }
        this.reasonPhrase = reasonPhrase;
    }

    @JsonValue
    public int value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    @Override
    public String toString() {
        return this.value + " " + reasonPhrase;
    }

    private static final StatusEnum[] VALUES;

    static {
        VALUES = values();
    }


    public static StatusEnum valueOf(int statusCode) {
        StatusEnum status = resolve(statusCode);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
        }
        return status;
    }

    @Nullable
    public static StatusEnum resolve(int statusCode) {

        for (StatusEnum status : VALUES) {
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }


}



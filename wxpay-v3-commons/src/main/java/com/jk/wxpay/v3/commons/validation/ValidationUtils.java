package com.jk.wxpay.v3.commons.validation;

import com.jk.wxpay.v3.commons.util.StringUtils;

public class ValidationUtils {

    public static void validate(Validator validator) throws RuntimeException {
        validator.validate();
    }

    public static void required(String name, String value) throws RuntimeException {
        if (StringUtils.isNullOrEmpty(value)) {
            throw new IllegalArgumentException(name + " is required, shouldn't be null or empty");
        }
    }

    public static void required(String name, Object value) throws RuntimeException {
        if (value == null) {
            throw new IllegalArgumentException(name + " is required, shouldn't be null or empty");
        }
    }
}

package com.fileupload.multipleloginregistrationspringboot.Annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueStockIdValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueStockId {
    String message() default "Stock ID is already taken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


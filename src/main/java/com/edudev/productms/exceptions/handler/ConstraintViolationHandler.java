package com.edudev.productms.exceptions.handler;

import com.edudev.productms.exceptions.BadRequestHttpException;

import javax.validation.Validation;
import javax.validation.Validator;


public final class ConstraintViolationHandler {

    public static void validate(final Object entity) {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final var violations = validator.validate(entity);

        if(!violations.isEmpty()) {
            final var violation = violations.stream().findFirst().get();
            throw new BadRequestHttpException(violation.getMessage());
        }
    }
}
package com.edudev.productms.exceptions;

public final class BadRequestHttpException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BadRequestHttpException(final String msg) { super(msg); }
}
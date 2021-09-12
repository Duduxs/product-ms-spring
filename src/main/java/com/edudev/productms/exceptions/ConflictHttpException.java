package com.edudev.productms.exceptions;

public final class ConflictHttpException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ConflictHttpException(final String msg) { super(msg); }
}
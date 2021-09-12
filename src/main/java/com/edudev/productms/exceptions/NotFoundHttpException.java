package com.edudev.productms.exceptions;

public final class NotFoundHttpException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotFoundHttpException() {
        this("Entidade não encontrada!");
    }

    public NotFoundHttpException(final String msg) { super(msg); }
}
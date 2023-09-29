package br.edu.ifpb.decexpress.utils.exception;

import br.edu.ifpb.decexpress.utils.message.MessageBundle;

import java.io.Serial;

public class ServiceApplicationException extends Exception {
    @Serial
    private static final long serialVersionUID = -3538495919342954385L;
    private static final String MESSAGE_DEFAUT = "message.default";
    public ServiceApplicationException(String message) {
        super(MessageBundle.getMessage(message));
    }

    public ServiceApplicationException(String message, Throwable cause) {
        super(MessageBundle.getMessage(message), cause);
    }
}

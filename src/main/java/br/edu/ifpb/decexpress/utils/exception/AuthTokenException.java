package br.edu.ifpb.decexpress.utils.exception;

import br.edu.ifpb.decexpress.utils.message.MessageBundle;

public class AuthTokenException extends RuntimeException{


    public AuthTokenException(String message) {
        super(MessageBundle.getMessage(message));
    }

    public AuthTokenException(String message, Throwable cause) {
        super(MessageBundle.getMessage(message), cause);
    }
}

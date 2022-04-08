/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.realestate.ctrl.app;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pathompong
 */
public class ApplicationException extends RuntimeException {

    public enum Type {
        incomplete_parameter(HttpServletResponse.SC_BAD_REQUEST),
        uncommited_transaction(HttpServletResponse.SC_NOT_ACCEPTABLE),
        internal_server_error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        public final int status;

        private Type(int status) {
            this.status = status;
        }

        public ApplicationException dispatch(String message, Throwable cause) {
            return new ApplicationException(this, message, cause);
        }

        public ApplicationException dispatch(String message) {
            return new ApplicationException(this, message, null);
        }

        public ApplicationException dispatch(Throwable cause) {
            return new ApplicationException(this, null, cause);
        }

        public ApplicationException dispatch() {
            return new ApplicationException(this, null, null);
        }
    }
    private final Type type;

    private ApplicationException(Type type, String message, Throwable cause) {
        super(message, cause);
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}

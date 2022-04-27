/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.realestate.ctrl.app;

import static javax.servlet.http.HttpServletResponse.*;

/**
 *
 * @author Pathompong
 */
public class ApplicationException extends RuntimeException {

    public enum Type {
        incomplete_parameter(SC_BAD_REQUEST),
        invalid_parameter_format(SC_BAD_REQUEST),
        verifier_mismatch(SC_CONFLICT),
        authentication_required(SC_UNAUTHORIZED),
        permission_denied(SC_FORBIDDEN),
        access_denied(SC_FORBIDDEN),
        uncommited_transaction(SC_NOT_ACCEPTABLE),
        internal_server_error(SC_INTERNAL_SERVER_ERROR);
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

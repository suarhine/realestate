/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.realestate.ctrl.app;

import static org.realestate.ctrl.app.ApplicationInstance.*;
import static org.reflex.invoke.functional.Triable.trie;

import java.util.function.Function;
import java.util.function.Supplier;
import javax.servlet.http.HttpServletRequest;
import org.realestate.db.entity.Users;
import org.realestate.db.fix.UsersFuncFix;

/**
 *
 * @author Pathompong
 */
public class Commons {

    public static void log(String message, Throwable thrown, int traceLevel) {
        var builder = new StringBuilder();
        if (message != null && !message.isBlank()) {
            builder.append("\r\n").append(message);
        }
        builder.append("\r\n\tat ").append(new Throwable().getStackTrace()[traceLevel]);
        if (thrown == null) {
            context.log(builder.toString());
        } else {
            context.log(builder.toString(), thrown);
        }
    }

    public static void log(String message, Throwable thrown) {
        log(message, thrown, 2);
    }

    public static void log(Throwable thrown) {
        log(null, thrown, 2);
    }

    public static void log(String message) {
        log(message, null, 2);
    }

    public static <X extends Throwable> X upper(X x, int lv) {
        try {
            var e = x.getStackTrace();
            System.arraycopy(e, lv, e = new StackTraceElement[e.length - lv], 0, e.length);
            x.setStackTrace(e);
        } catch (Throwable xx) {
        }
        return x;
    }

    public static <T, X extends Throwable> T notnull(
            T value, Supplier<X> throwing
    ) throws X {
        if (value == null) {
            try {
                throw throwing.get();
            } catch (NullPointerException x) {
                throw upper(throwing == null ? new NullPointerException() : x, 2);
            }
        }
        return value;
    }

    public static <T, X extends Throwable> T[] notnull(
            T[] value, int size, Supplier<X> throwing
    ) throws X {
        if (notnull(value, throwing).length < size) {
            try {
                throw throwing.get();
            } catch (NullPointerException x) {
                throw upper(throwing == null ? new NullPointerException() : x, 2);
            }
        }
        return value;
    }

    public static <T, X extends Throwable> T notnull(
            Supplier<T> value, Function<Throwable, X> throwing
    ) throws X {
        return notnull(trie(() -> value.get(), x -> {
            throw throwing.apply(x);
        }), () -> throwing.apply(null));
    }

    public static <T, X extends Throwable> T[] notnull(
            Supplier<T[]> value, int size, Function<Throwable, X> throwing
    ) throws X {
        return notnull(trie(() -> value.get(), x -> {
            throw throwing.apply(x);
        }), size, () -> throwing.apply(null));
    }

    public static <T, X extends Throwable> T notnull(T value, String message)
            throws NullPointerException {
        return notnull(value, () -> upper(new NullPointerException(message), 3));
    }

    public static <T, X extends Throwable> T[] notnull(T[] value, int size, String message)
            throws NullPointerException {
        return notnull(value, size, () -> upper(new NullPointerException(message), 3));
    }

    public static <T, X extends Throwable> T notnull(T value)
            throws NullPointerException {
        return notnull(value, () -> upper(new NullPointerException(), 3));
    }

    public static <T, X extends Throwable> T[] notnull(T[] value, int size)
            throws NullPointerException {
        return notnull(value, size, () -> upper(new NullPointerException(), 3));
    }

    public static <T> T require(Supplier<T> value, String message)
            throws IllegalArgumentException {
        return notnull(value, x -> upper(new IllegalArgumentException(message), 3));
    }

    public static <T> T[] require(Supplier<T[]> value, int size, String message)
            throws IllegalArgumentException {
        return notnull(value, size, x -> upper(new IllegalArgumentException(message), 3));
    }

    public static <T> T require(T value, String message)
            throws IllegalArgumentException {
        return notnull(value, () -> upper(new IllegalArgumentException(message), 3));
    }

    public static <T> T[] require(T[] value, int size, String message)
            throws IllegalArgumentException {
        return notnull(value, size, () -> upper(new IllegalArgumentException(message), 3));
    }

    public static <T, X extends Throwable> T conflict(
            T v1, T v2, Supplier<X> throwing
    ) throws X {
        if (v1 == null ? v2 != null : !v1.equals(v2)) {
            throw throwing.get();
        }
        return v1;
    }

    public static <T, X extends Throwable> T conflict(
            Supplier<T> v1,
            Supplier<T> v2,
            Function<Throwable, X> throwing
    ) throws X {
        return conflict(trie(() -> v1.get(), x -> {
            throw throwing.apply(x);
        }), trie(() -> v2.get(), x -> {
            throw throwing.apply(x);
        }), () -> throwing.apply(null));
    }

    public static <T> T conflict(T v1, T v2, String message)
            throws IllegalArgumentException {
        return conflict(v1, v2, () -> upper(new UnsupportedOperationException(message), 3));
    }

    public static void clear() {
        model().close();
        System.gc();
    }

    private static boolean allow(Users user, UsersFuncFix... require) {
        if (!Boolean.TRUE.equals(user.getActive())) {
            return false;
        }
        try {
            for (var role : user.getUsersRolesList()) {
                for (var func : role.getUsersFuncList()) {
                    for (var r : require) {
                        if (r.allow(func.getId())) {
                            return true;
                        }
                    }
                }
            }
        } catch (NullPointerException x) {
        }
        return require == null || require.length == 0;
    }

    public static Users access(HttpServletRequest request, UsersFuncFix... require) {
        try {
            var user = (Users) request.getSession(false).getAttribute("USER");
            if (allow(user, require)) {
                return user;
            }
            if (Boolean.TRUE.equals(user.getActive())) {
                throw ApplicationException.Type.permission_denied.dispatch();
            } else {
                throw ApplicationException.Type.access_denied.dispatch();
            }
        } catch (NullPointerException | ClassCastException x) {
            throw ApplicationException.Type.authentication_required.dispatch(x);
        }
    }

    public static boolean allow(HttpServletRequest request, UsersFuncFix... require) {
        try {
            return allow((Users) request.getSession(false).getAttribute("USER"), require);
        } catch (NullPointerException | ClassCastException x) {
            return false;
        }
    }
}

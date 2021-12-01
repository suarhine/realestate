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
                throw throwing == null ? new NullPointerException() : x;
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

    public static <T, X extends Throwable> T notnull(T value, String message)
            throws NullPointerException {
        return notnull(value, () -> upper(new NullPointerException(message), 3));
    }

    public static <T, X extends Throwable> T notnull(T value)
            throws NullPointerException {
        return notnull(value, () -> upper(new NullPointerException(), 3));
    }

    public static <T> T require(Supplier<T> value, String message)
            throws IllegalArgumentException {
        return notnull(value, x -> upper(new IllegalArgumentException(message), 3));
    }

    public static <T> T require(T value, String message)
            throws IllegalArgumentException {
        return notnull(value, () -> upper(new IllegalArgumentException(message), 3));
    }

    public static <X extends Throwable> void conflict(
            Object v1, Object v2, Supplier<X> throwing
    ) throws X {
        if (v1 == null ? v2 != null : !v1.equals(v2)) {
            throw throwing.get();
        }
    }

    public static <X extends Throwable> void conflict(
            Supplier<Object> v1,
            Supplier<Object> v2,
            Function<Throwable, X> throwing
    ) throws X {
        conflict(trie(() -> v1.get(), x -> {
            throw throwing.apply(x);
        }), trie(() -> v2.get(), x -> {
            throw throwing.apply(x);
        }), () -> throwing.apply(null));
    }

    public static void conflict(Object v1, Object v2, String message)
            throws IllegalArgumentException {
        conflict(v1, v2, () -> upper(new UnsupportedOperationException(message), 3));
    }

    public static void clear() {
        model().close();
        System.gc();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.realestate.ctrl.app;

import java.util.Locale;
import java.util.function.Function;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import org.persistence.model.Model;

/**
 *
 * @author Pathompong
 */
public class ApplicationInstance {

    protected static ServletContext context;

    public static ServletContext context() {
        if (context == null) {
            throw new IllegalStateException("Service No Deployed.");
        }
        return context;
    }

    public static Model.Factory model() {
        return new Model.Factory.Impl("realestate-pu") {
            @Override
            public <R> R persistence(Function<EntityManagerFactory, R> fn) {
                try {
                    return super.persistence(fn); //To change body of generated methods, choose Tools | Templates.
                } catch (RuntimeException x) {
                    x.printStackTrace();
                    throw x;
                }
            }
        };
    }

    public static <E, I, M extends Model<E, I>> M model(Class<E> entity, Class<I> id) {
        return model().create(entity, id);
    }

    public static <E, I, M extends Model<E, I>> M model(Class<E> entity) {
        return model(entity, null);
    }

    public static Locale locale() {
        return new Locale("th", "TH");
    }
}

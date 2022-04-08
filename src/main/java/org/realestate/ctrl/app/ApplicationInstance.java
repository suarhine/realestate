/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.realestate.ctrl.app;

import java.util.Arrays;
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
                    return super.persistence(factory -> {
                        factory.getCache().evictAll();
                        return fn.apply(factory);
                    });
                } catch (RuntimeException x) {
                    x.printStackTrace();
                    throw x;
                }
            }

            @Override
            public <E, R> R jpql(Model.Result<E, R> fn, Class<E> clazz, CharSequence statement, Object... params) {
                System.out.println(statement);
                System.out.println(Arrays.toString(params));
                return super.jpql(fn, clazz, statement, params); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <R> R jpql(Model.Result.Raw<R> fn, CharSequence statement, Object... params) {
                System.out.println(statement);
                System.out.println(params);
                return super.jpql(fn, statement, params); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <E, R> R sql(Model.Result.Raw<R> fn, Class<E> clazz, CharSequence statement, Object... params) {
                return manager(manager -> fn.apply(inject(clazz == Object.class
                        ? manager.createNativeQuery(statement.toString())
                        : manager.createNativeQuery(statement.toString(), clazz),
                        params
                )));
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

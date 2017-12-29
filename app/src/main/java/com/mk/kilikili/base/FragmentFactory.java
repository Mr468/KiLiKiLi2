package com.mk.kilikili.base;

/**
 * Created by Mr_468 on 2017/3/30.
 */

public class FragmentFactory {

    public static <T> T createFragment(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

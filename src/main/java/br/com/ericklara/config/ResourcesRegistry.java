package br.com.ericklara.config;

import java.util.ArrayList;
import java.util.List;

public class ResourcesRegistry<T> {
    private static ResourcesRegistry<?> instance;
    private List<Class<? extends T>> annotatedClasses;

    private ResourcesRegistry() {
        this.annotatedClasses = new ArrayList<>();
    }

    public static synchronized ResourcesRegistry getInstance() {
        if(instance == null) {
            instance = new ResourcesRegistry();
        }
        return instance;
    }

    public void addClass(Class<? extends T> classToAdd) {
        annotatedClasses.add(classToAdd);
    }

    public List<Class<? extends T>> getAnnotatedClasses() {
        return annotatedClasses;
    }
}

package br.com.ericklara.config;

import br.com.ericklara.annotations.TestAnnotation;
import org.reflections.Reflections;

import java.util.Set;

public class ResourcesInitializer {

    public static void initialize() {
        Reflections reflections = new Reflections("br.com.ericklara");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(TestAnnotation.class);
        ResourcesRegistry instance = ResourcesRegistry.getInstance();
        classes.forEach(instance::addClass);
    }
}

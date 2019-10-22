package asellion.mock;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

import asellion.mock.controllers.AsellionController;

@ApplicationPath("/")
public class MyApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        // register root resource
        classes.add(AsellionController.class);
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return new HashSet<>();
    }
}

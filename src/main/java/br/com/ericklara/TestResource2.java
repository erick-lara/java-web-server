package br.com.ericklara;

import br.com.ericklara.annotations.TestAnnotation;

@TestAnnotation
public class TestResource2 extends DefaultResource {

    public TestResource2() {
        super("/test2");
    }

    @Override
    public String execute() {
        return "<h1>Hello from custom resource 2</h1>";
    }
}

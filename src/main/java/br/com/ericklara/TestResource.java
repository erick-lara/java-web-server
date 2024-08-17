package br.com.ericklara;

import br.com.ericklara.annotations.TestAnnotation;

@TestAnnotation
public class TestResource extends DefaultResource {

    public TestResource() {
        super("/test");
    }

    @Override
    public String execute() {
        return "<h1>Hello from custom resource</h1>";
    }
}

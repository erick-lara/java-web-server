package br.com.ericklara;

public abstract class DefaultResource {
    public String route;

    public DefaultResource(String route) {
        this.route = route;
    }

    public abstract String execute();

    public String getRoute() {
        return route;
    }
}

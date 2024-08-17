package br.com.ericklara;


import br.com.ericklara.config.ResourcesInitializer;
import br.com.ericklara.config.ResourcesRegistry;
import br.com.ericklara.factory.ServerThreadFactory;
import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Init {

    private static final Logger LOG = LogManager.getLogger(Init.class);
    private ExecutorService threadPoolExecutor;
    private HttpServer httpServer;

    private Init() {
        try {
            System.out.println("caiu hein");
            this.httpServer = HttpServer
                    .create(new InetSocketAddress(8080), 1);
            this.threadPoolExecutor = Executors.newCachedThreadPool(new ServerThreadFactory());
            httpServer.setExecutor(threadPoolExecutor);
        } catch (IOException ex) {
            LOG.error("Exception caugth when starting server {}", ex.getCause(), ex);
            System.exit(1);
        }
    }

    public static void main(String[] args) throws IOException {
        var init = new Init();
        init.start();
    }

    public void start() {
        try {
            ResourcesInitializer.initialize();
            ResourcesRegistry<DefaultResource> instance = ResourcesRegistry.getInstance();
            for (Class<? extends DefaultResource> clazz : instance.getAnnotatedClasses()) {
                DefaultResource castedClass = clazz.getDeclaredConstructor().newInstance();
                this.httpServer
                        .createContext(castedClass.route, exchange -> {
                            String response = castedClass.execute();
                            exchange.sendResponseHeaders(200, response.length());
                            exchange.getResponseHeaders()
                                    .set("Content-Type", "text/html; charset=" + StandardCharsets.UTF_8);
                            OutputStream res = exchange.getResponseBody();
                            res.write(response.getBytes(StandardCharsets.UTF_8));
                            res.close();
                        });
            }
            this.httpServer.start();
        } catch (Exception ex) {}
    }


}
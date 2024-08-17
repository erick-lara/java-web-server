package br.com.ericklara.factory;

import java.util.concurrent.ThreadFactory;
import java.util.logging.Logger;

public class ServerThreadFactory implements ThreadFactory {

    private static int threadNumber = 1;
    private static final Logger LOG = Logger.getLogger(ServerThreadFactory.class.getName());

    @Override
    public Thread newThread(Runnable r) {
        Thread newThread = new Thread(r, "web-server-thread-" + threadNumber);
        threadNumber++;
        newThread.setUncaughtExceptionHandler(
                (thread, exception) -> {
                    LOG.warning("Uncaught exception in thread: " + thread);
                    LOG.warning(exception.getLocalizedMessage());
                }
        );
        newThread.setDaemon(true);
        return newThread;
    }
}

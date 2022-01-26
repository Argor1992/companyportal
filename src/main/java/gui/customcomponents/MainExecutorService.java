package gui.customcomponents;

import java.util.concurrent.*;

/**
 * @author Thorsten Zieres, 1297197
 */
public class MainExecutorService {
    private static final MainExecutorService ourInstance = new MainExecutorService();
    public static MainExecutorService getInstance() { return ourInstance; }
    private MainExecutorService() { }

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public void schedule(Runnable runnable, long delay, TimeUnit timeUnit) {
        executorService.schedule(runnable, delay, timeUnit);
    }
}

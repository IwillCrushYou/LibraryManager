import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Logger extends Thread {
    private static final Queue<String> logsBuffer = new LinkedList<>();
    private static boolean running = false;
    private static Logger writerThread;

    public Logger() {
        super("logger-writer");
        setDaemon(true);
    }

    @Override
    public void run() {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("logs.txt", true))) {
            while (running || !logsBuffer.isEmpty()) {
                String log = null;

                synchronized (logsBuffer) {
                    while (logsBuffer.isEmpty() && running) {
                        logsBuffer.wait();              // sleep until notified
                    }
                    if (!logsBuffer.isEmpty()) {
                        log = logsBuffer.poll();        // pick one log
                    }
                }

                if (log != null) {
                    fileWriter.write(log);
                    fileWriter.newLine();
                    fileWriter.flush();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void startLogger() {
        if (running) return;

        running = true;
        writerThread = new Logger();
        writerThread.start();
    }

    public static void log(String message) {
        if (!running) startLogger();

        synchronized (logsBuffer) {
            logsBuffer.offer(message);
            logsBuffer.notify();                        // wake up the writer thread
        }
    }

    public static void shutdown() {
        if (!running) return;

        running = false;
        synchronized (logsBuffer) {
            logsBuffer.notify();                        // wake writer so it can exit
        }
        if (writerThread != null) {
            try {
                writerThread.join();                    // wait for writer to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
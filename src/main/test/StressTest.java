import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

public class StressTest {

    // NOTE: Run webserver first
    public static void main(String[] args) {
        StressTest st = new StressTest();

        int numThreads = 10;

        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch finishLatch = new CountDownLatch(numThreads);

        for (int i = 0; i < numThreads; i++) {
            new Thread(() -> {
                try {
                    startLatch.await();

                    for (int j = 0; j < 100; j++) {
                        st.stressTest_search();
                    }

                    finishLatch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        long startTime = System.currentTimeMillis();
        startLatch.countDown();
        try {
            finishLatch.await();
            long finish = System.currentTimeMillis();
            long timeSpent = finish - startTime;
            System.out.println(timeSpent + "ms to process all requests.");

        } catch (Exception ignored) {

        }
    }

    void stressTest_search() {
        try {
            URL url = new URL("http://localhost:5008/product/search");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            int httpStatus = conn.getResponseCode();

            if (httpStatus >= 400) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
    }
}

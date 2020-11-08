package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;

public class BigBrother implements Runnable{

    private final CountDownLatch cdl;
    private final DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");
    private int total = 0;

    public BigBrother(int quorom){
        cdl = new CountDownLatch(quorom);
    }
    @Override
    public void run() {
        System.out.printf("%s -> Waiting for quorum to start the recollect\n",
                LocalDateTime.now().format(f));

        try {
            cdl.await();
            System.out.printf("%s -> We have quorum. The Recollect started...\n",
                    LocalDateTime.now().format(f));
        } catch (InterruptedException e) {
            System.out.println("The Big Brother has been interrupted while waiting to have quorum ");
        }

    }

    public void addTotal(String friend, int q){
        total += q;
        System.out.printf("%s -> %s paid his/her part %d €. Just left %d €\n",
                LocalDateTime.now().format(f),
                friend,
                q,
                cdl.getCount()-total);
        for (int i = 0; i < q; i++) {
            cdl.countDown();
        }
    }
}

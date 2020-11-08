package classes;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Friend implements Runnable{
    private final int q;
    private final BigBrother bg;
    private final String name;
    public Friend(int q, String name, BigBrother bg){
        if(q < 1 || name == null || bg == null){
            throw new IllegalArgumentException();
        }
        this.q = q;
        this.name = name;
        this.bg = bg;
    }
    

    @Override
    public void run() {
        try {
            pay();
        } catch (InterruptedException e) {
            System.out.println("I've been interrupted while paid");
            return;
        }
        bg.addTotal(name, q);
    }

    private void pay() throws InterruptedException {
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(9)); // Duerme

    }
}

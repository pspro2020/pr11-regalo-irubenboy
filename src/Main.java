import classes.BigBrother;
import classes.Friend;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final int QUORUM = 10;

    public static void main(String[] args) {
        BigBrother bg = new BigBrother(QUORUM);
        new Thread(bg).start();
        for (int i = 0; i < 10; i++) {
            new Thread(new Friend(
                    ThreadLocalRandom.current().nextInt(4)+1,
                    "Friend #" + i,
                    bg
            )).start();
        }
    }
}

import java.util.concurrent.locks.ReentrantLock;

public class Lock {

    public static void main(String[] args) {
        Buy buyer1 = new Buy();

        new Thread(buyer1).start();
        new Thread(buyer1).start();
    }
}


class Buy implements Runnable{

    int tickets = 10;
    final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {

        while (true){

            lock.lock();

            if (tickets<=0){
                System.out.println("insufficient tickets!");
                return;
            }

            tickets--;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(tickets);

            lock.unlock();
        }

    }
}


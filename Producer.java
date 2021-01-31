import java.util.LinkedList;
import java.util.Queue;


class Buffer{

    private Queue<Integer> queue = new LinkedList<>();
    private final int capacity = 5;

    // 锁 保证线程安全
    // 实现线程通信
    public synchronized void add(int i) throws InterruptedException {
        if (queue.size()>capacity){
            System.out.println("waiting for consumer to consume!");
            wait(); // stop producing
        }

        queue.add(i);
        notify(); // notify consumer
    }

    public synchronized int take() throws InterruptedException {
        if (queue.size() == 0){
            System.out.println("waiting for producer to produce!");
            wait();
        }

        int val = queue.poll();
        notify();
        return val;
    }
}


// 共享数据区域 生产者生产过多 - 阻塞
// 多线程协同问题
public class Producer implements Runnable{

    private Buffer buffer;

    Producer(Buffer b){
        this.buffer = b;
    }

    @Override
    public void run(){
        for (int i=0; i<10; i++){
            try {
                buffer.add(i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Consumer consumer = new Consumer(buffer);
        Producer producer = new Producer(buffer);

        new Thread(consumer).start();
        new Thread(producer).start();
    }
}

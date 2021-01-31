public class Consumer implements Runnable{

    private Buffer buffer;

    Consumer(Buffer b){
        this.buffer = b;
    }

    @Override
    public void run() {

        for (int i=0; i<10; i++){
            try {
                System.out.println(buffer.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

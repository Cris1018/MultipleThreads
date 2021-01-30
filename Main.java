
class Test1 implements Runnable{

    int tickets = 200;
    boolean flag = true;

    int forA;
    int forB;
    int forC;

    @Override
    public void run() {
        while (flag){
            try{
                buy();
            } catch (InterruptedException e) {

            }
        }
    }

    public synchronized void buy() throws InterruptedException {

        if (tickets<=0){
            flag = false;
            System.out.println("-----------");
            System.out.println("A "+forA);
            System.out.println("B "+forB);
            System.out.println("C "+forC);
            System.out.println("-----------");
            return;
        }

        Thread.sleep(200);

        System.out.println(tickets+ " "+ Thread.currentThread().getName());

        if (Thread.currentThread().getName().equals("A")){
            forA++;
        } else if (Thread.currentThread().getName().equals("B")){
            forB++;
        } else {
            forC++;
        }

        tickets--;
    }
}


public class Main {

    public static void main(String[] args) {
        Test1 test = new Test1();
        new Thread(test, "A").start();
        new Thread(test, "B").start();
        new Thread(test, "C").start();
    }
}

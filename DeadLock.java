class Mirror{}
class Lipstick{}

class MakeUp implements Runnable{

    static Mirror mirror = new Mirror();
    static Lipstick lipstick = new Lipstick();
    String name;
    int choice;

    public MakeUp(int choice, String name){
        this.name = name;
        this.choice = choice;
    }
    @Override
    public void run() {
        try {
            makeUp();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void makeUp() throws InterruptedException {
        if (this.choice == 0){
            synchronized (mirror){
                System.out.println("get the mirror!!! "+this.name);
                Thread.sleep(1000);
            }
            synchronized (lipstick){
                System.out.println("get the lipstick!!! "+this.name);
            }
        } else{
            synchronized (lipstick){
                System.out.println("get the lipstick!!! "+this.name);
                Thread.sleep(2000);
            }
            synchronized (mirror){
                System.out.println("get the mirror!!! "+this.name);
            }
        }
    }

}

public class DeadLock{

    public static void main(String[] args) {
        MakeUp m1 = new MakeUp(0, "grace");
        MakeUp m2 = new MakeUp(2, "christine");

        new Thread(m1).start();
        new Thread(m2).start();
    }
}

class Account{
    int amt;

    public Account(int amt){
        this.amt = amt;
    }
}

public class DrawingFromBank extends Thread {

    // 只有一个资源
    static Account account = new Account(1000);
    int amount;
    String people;

    public DrawingFromBank(String people, int amount){
        // this.account = account;
        this.amount = amount;
        this.people = people;
    }

    public void draw(){

        while (true){
            synchronized (account) {
                if (this.account.amt<this.amount){
                    System.out.println("insufficient amount!!! "+people);
                    return;
                }

                this.account.amt -= this.amount;
                System.out.println("success:) " + people + " " + this.account.amt);

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

//            if (this.account.amt < this.amount){
//                System.out.println("insufficient amount!!!");
//                return;
//            }

//            this.account.amt -= this.amount;
//            System.out.println("success:)");

    }

    public void run(){
        draw();
    }

    public static void main(String[] args) {

        Account account = new Account(1000);
        DrawingFromBank you = new DrawingFromBank("you",70);
        DrawingFromBank girl = new DrawingFromBank("girl",50);

        you.start();
        girl.start();
    }
}

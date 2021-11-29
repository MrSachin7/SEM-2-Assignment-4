package app.producer;

import app.adapterqueue.Deposit;
import app.valuable.Mine;
import app.valuable.Valuable;

public class Miner implements Runnable {

    private Deposit deposit;
    private Mine mine;

    public Miner(Deposit deposit, Mine mine) {
        this.deposit = deposit;
        this.mine=mine;
    }

    @Override
    public void run() {
        while (true) {
            Valuable valuable = mine.getValuable();
            deposit.add(valuable);
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

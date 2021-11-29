package app.consumer;

import app.adapterqueue.Deposit;
import app.guardsman.TreasureRoomDoor;
import app.logger.Log;
import app.valuable.Valuable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Transporter implements Runnable {
    private Deposit deposit;
    private Random randomGenerator = new Random();
    private List<Valuable> transportBag;
    private TreasureRoomDoor treasureRoom;


    public Transporter(Deposit deposit, TreasureRoomDoor treasureRoom) {
        this.deposit = deposit;
        this.treasureRoom = treasureRoom;
        transportBag = new ArrayList<>();
    }

    @Override
    public void run() {
        while (true) {
            double random = randomGenerator.nextInt(200 - 50) + 50;
            double value = 0;
            while (value < random) {
                try {
                    Valuable valuable = deposit.get();
                    transportBag.add(valuable);
                    value += valuable.getValue();
                } catch (Exception e) {
                    Log.getInstance().log(e.getMessage());
                }
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Log.getInstance().log("Taking the values to the safe..will take 5 seconds");
            }
            treasureRoom.acquireWrite();
            for (Valuable i : transportBag
            ) {
                treasureRoom.add(i);
            }
            treasureRoom.releaseWrite();
            // imitating that with the print to the console
            transportBag.clear();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

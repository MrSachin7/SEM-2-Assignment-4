package app;

import app.adapterqueue.Deposit;
import app.consumer.Transporter;
import app.guardsman.GuardsMan;
import app.guardsman.TreasureRoomDoor;
import app.kingAndAccountant.Accountant;
import app.kingAndAccountant.King;
import app.producer.Miner;
import app.valuable.Mine;

public class Main {
    public static void main(String[] args) {
        Deposit deposit = new Deposit();
        Mine mine = new Mine();
        TreasureRoomDoor treasureRoomDoor = new GuardsMan();
        King theKing = new King(treasureRoomDoor);
        Accountant accountant = new Accountant(treasureRoomDoor);

        for (int i = 0; i < 3; i++) {                                // Three miners
            new Thread(new Miner(deposit,mine)).start();
        }
        for (int i = 0; i < 1; i++) {
            new Thread(new Transporter(deposit,treasureRoomDoor)).start();
        }

        new Thread(theKing).start();
        new Thread(accountant).start();
    }
}

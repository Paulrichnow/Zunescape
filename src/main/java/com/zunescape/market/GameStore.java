package com.zunescape.market;

import com.zunescape.util.PlayerDb;

import java.util.Scanner;

public class GameStore {

    private PlayerDb playerDb;

    public GameStore(PlayerDb playerDb) {
        this.playerDb = playerDb;
    }

    public void enter() {
        Scanner scnr = new Scanner(System.in);
        System.out.println("What would you like to buy?\n" +
                "1) Zealth Potion\n" +
                "2) Zana Potion\n" +
                "3) Zune Zattleaxe\n" +
                "4) Zragon Zcimitar\n" +
                "5) Zorags Zlatebody\n" +
                "Enter the number for your purchase.");
        String userBuyChoice = scnr.next();
        if (userBuyChoice.equals("1")) {
            playerDb.addItem("Zealth Potion");
        }
        else if (userBuyChoice.equals("2")) {
            playerDb.addItem("Zana Potion");
        }
        else if (userBuyChoice.equals("3")) {
            playerDb.addItem("Zune Zattleaxe");
        }
        else if (userBuyChoice.equals("4")) {
            playerDb.addItem("Zragon Zcimitar");
        }
        else if (userBuyChoice.equals("5")) {
            playerDb.addItem("Zorags Zlatebody");
        }
    }
}

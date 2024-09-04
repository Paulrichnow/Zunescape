package com.zunescape.character;

import com.zunescape.util.PlayerDb;

import java.util.Locale;
import java.util.Scanner;

public class CharacterMenu {

    private PlayerDb playerDb;

    public CharacterMenu(PlayerDb playerDb) {
        this.playerDb = playerDb;
    }

    public void open() {
        Scanner scnr = new Scanner(System.in);
        System.out.println("What items would you like to equip?\n" +
                "Type done when finished.");

        while (true) {

            for (String item : playerDb.getItems()) {
                System.out.println(item);
            }
            String userEquipping = scnr.nextLine();

            if (userEquipping.toLowerCase(Locale.ROOT).equals("done")) {
                return;
            }

            System.out.println("Equipped " + userEquipping);
        }
    }
}

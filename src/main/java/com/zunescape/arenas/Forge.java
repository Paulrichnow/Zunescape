package com.zunescape.arenas;

import java.util.Locale;
import java.util.Scanner;

public class Forge {
    private Arenas arenas;
    private Tourneys tourneys;

    public Forge(Arenas arenas, Tourneys tourneys) {
        this.arenas = arenas;
        this.tourneys = tourneys;
    }

    public void enter() {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Do you want to create an Arena or Tourney?");
        String userChoice = scnr.nextLine();

        if (userChoice.toLowerCase(Locale.ROOT).equals("arena")) {
            System.out.println("Which arena?");
            String arenaName = scnr.nextLine();
            arenas.addArena(arenaName);
        }
        else if (userChoice.toLowerCase(Locale.ROOT).equals("tourney")) {
            System.out.println("Tourney name?");
            String tourneyName = scnr.nextLine();
            tourneys.addTourney(tourneyName);
        }

    }
}

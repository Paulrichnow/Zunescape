package com.zunescape.royale;

import com.zunescape.arenas.Arenas;
import com.zunescape.arenas.Tourneys;
import com.zunescape.royale.chat.Chat;
import com.zunescape.royale.util.Contestant;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class BattleRoyale {
    private Arenas arenas;
    private Tourneys tourneys;
    private Chat chat;

    public BattleRoyale(Arenas arenas, Tourneys tourneys, Chat chat) {
        this.arenas = arenas;
        this.tourneys = tourneys;
        this.chat = chat;
    }



    public void battle(Set contestants, Contestant primary, String arenaIDorTourneyInvite) {
        if (tourneys.hasInvite(arenaIDorTourneyInvite)) {
            String tourneyName = tourneys.loadTourney(arenaIDorTourneyInvite);
            System.out.println("Welcome to the tournament " + tourneyName);
            System.out.println("You Win!");
        } else {
            System.out.println("Welcome to " + arenaIDorTourneyInvite);

            chat.post(contestants, primary, "Hello, World!");
            ArrayList<Contestant> contestantList = new ArrayList<Contestant>(contestants);
            Random rand = new Random();
            Contestant randomContestant = contestantList.get(rand.nextInt(contestants.size()));
            System.out.println(randomContestant.getUsername() + " wins!");
        }
    }


}

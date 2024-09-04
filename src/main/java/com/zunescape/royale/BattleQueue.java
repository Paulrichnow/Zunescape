package com.zunescape.royale;

import com.zunescape.arenas.Arenas;
import com.zunescape.arenas.Tourneys;
import com.zunescape.player.Player;
import com.zunescape.player.util.AbstractPlayerFactory;
import com.zunescape.royale.util.Contestant;

import java.sql.SQLOutput;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;


public class BattleQueue {

    private Arenas arenas;
    private AbstractPlayerFactory battleMatcher;
    private BattleRoyale battleRoyale;


    public BattleQueue(Arenas arena, AbstractPlayerFactory battleMatcher, BattleRoyale battleRoyale) {
        this.arenas = arena;
        this.battleMatcher = battleMatcher;
        this.battleRoyale = battleRoyale;
    }


    public void enqueue() {
        String arena = selectArena();

        Set<Player> players = battleMatcher.selectPlayersWhere(new String[]{String.format("ARENA=%s", arena)});

        Set<Contestant> contestants = players.stream().map(player->Contestant.From(player)).collect(Collectors.toSet());


        battleRoyale.battle(contestants, Contestant.From(battleMatcher.getPrimaryPlayer()), arena);

    }

    private String selectArena() {
        Scanner scnr = new Scanner(System.in);


        System.out.println("Please select an arena among the following:");
        System.out.println(arenas.getArenas());

        String arenaChoice = scnr.nextLine();
        System.out.println(arenaChoice);

        return arenaChoice;
    }

}

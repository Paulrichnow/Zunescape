package com.zunescape.app;

import com.zunescape.app.util.AppPlayerFactory;
import com.zunescape.arenas.Arenas;
import com.zunescape.arenas.Forge;
import com.zunescape.arenas.Tourneys;
import com.zunescape.character.CharacterMenu;
import com.zunescape.market.GameStore;
import com.zunescape.market.PlayerExchange;
import com.zunescape.player.Player;
import com.zunescape.royale.BattleMatcher;
import com.zunescape.royale.BattleQueue;
import com.zunescape.royale.BattleRoyale;
import com.zunescape.royale.chat.Chat;
import com.zunescape.royale.chat.Whisper;
import com.zunescape.royale.util.Contestant;
import com.zunescape.royale.util.RangeValidator;
import com.zunescape.util.PlayerDb;

import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class App {


    public static void main(String[] args) {

        boolean checker = true;
        Contestant contestant = new Contestant("Memes", 12);
        Scanner scnr = new Scanner(System.in);
        Arenas arenas = new Arenas();
        Tourneys tourneys = new Tourneys();

        Chat chat = new Chat(new RangeValidator());
        BattleRoyale battleRoyale = new BattleRoyale(arenas, tourneys, chat);
        PlayerDb playerDb = new PlayerDb();
        GameStore gameStore = new GameStore(playerDb);
        PlayerExchange playerExchange = new PlayerExchange(playerDb);
        CharacterMenu characterMenu = new CharacterMenu(playerDb);

        AppPlayerFactory appPlayerFactory = new AppPlayerFactory();
        Player primaryPlayer = appPlayerFactory.getPrimaryPlayer();
        BattleMatcher battleMatcher = new BattleMatcher(primaryPlayer);
        BattleQueue battleQueue = new BattleQueue(arenas, battleMatcher, battleRoyale);


        while (checker) {
            System.out.println("Welcome to Ze Wilds of Zunescape!\nWhat would you like to do?");
            System.out.println("1)\tBattle\n" +
                    "2)\tForge\n" +
                    "3)\tPurchase\n" +
                    "4)\tTrade\n" +
                    "5)\tAccessorize\n" +
                    "6)\tQuit");

            String userInput = scnr.next();

            if (userInput.equals("1")||userInput.toLowerCase(Locale.ROOT).equals("battle")) {
                System.out.println(userInput);
                System.out.println("Would you like to enter a tourney or battle?");
                String enterTourney = scnr.next();

                if (enterTourney.toLowerCase(Locale.ROOT).equals("tourney")) {
                    String tourneyInvite = tourneys.addTourney("Tourney");
                    System.out.println("Here is the tourney invite: " + tourneyInvite);
                    System.out.println("Enter the tourney key:");
                    String tourneyKey = scnr.next();


                    Set contestants = battleMatcher.findContestants(contestant, tourneyKey);
                    battleRoyale.battle(contestants, contestant, tourneyKey);
                    chat.setDecorator(new Whisper());

                }else {
                    battleQueue.enqueue();
                }
            }
            else if (userInput.equals("2")||userInput.toLowerCase(Locale.ROOT).equals("forge")) {
                Forge forge = new Forge(arenas, tourneys);

                forge.enter();

            }
            else if (userInput.equals("3") || userInput.toLowerCase(Locale.ROOT).equals("purchase")) {
                gameStore.enter();
            }
            else if (userInput.equals("4") || userInput.toLowerCase(Locale.ROOT).equals("trade")) {
                playerExchange.enter();
            }
            else if (userInput.equals("5") || userInput.toLowerCase(Locale.ROOT).equals("accessorize")) {
                characterMenu.open();
            }
            else if (userInput.equals("6") || userInput.toLowerCase(Locale.ROOT).equals("quit")) {
                checker = false;
            }
        }

    }
}

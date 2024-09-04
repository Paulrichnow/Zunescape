package com.zunescape.royale.util;

import com.zunescape.player.Player;

public class Contestant implements Player {

    private String playerId;
    private int elo;

    public Contestant(String playerId, int elo) {
        this.playerId = playerId;
        this.elo = elo;
    }

    public int getElo() {
        return elo;
    }

    @Override
    public String toString() {
        return playerId +
                " (Elo=" + elo + ")";
    }

    @Override
    public String getPUID() {
        return String.valueOf(playerId.hashCode());
    }

    @Override
    public String getUsername() {
        return playerId;
    }

    public static Contestant From(Player player) {
        if(player instanceof Contestant) {
            return (Contestant) player;
        }
        else {
            return new Contestant(player.getUsername(), 800);
        }
    }
}

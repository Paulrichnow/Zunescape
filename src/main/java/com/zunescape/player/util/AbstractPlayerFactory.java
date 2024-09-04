package com.zunescape.player.util;

import com.zunescape.player.Player;

import java.util.Set;

public interface AbstractPlayerFactory {

    public Player getPrimaryPlayer();

    default public Set<Player> selectPlayersWhere(String[] params) {
        return Set.of();
    }
}


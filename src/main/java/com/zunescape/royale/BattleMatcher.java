package com.zunescape.royale;

import com.zunescape.player.Player;
import com.zunescape.player.util.AbstractPlayerFactory;
import com.zunescape.royale.util.Contestant;

import java.util.Set;

public class BattleMatcher implements AbstractPlayerFactory {

    private Player primary;

    public BattleMatcher(Player primary) {
        this.primary = primary;
    }

    public Set findContestants(Contestant primary, String arena) {


        return Set.of(new Contestant("Mike", 12), new Contestant("Tom", 12), new Contestant("Paul", 12), primary );
    }

    @Override
    public Player getPrimaryPlayer() {
        return primary;
    }

    @Override
    public Set<Player> selectPlayersWhere(String[] params) {
        String arena = "";

        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue[0].equals("ARENA")){
                arena = keyValue[1];
            }
        }

        Contestant contestant = Contestant.From(this.getPrimaryPlayer());
        return findContestants(contestant, arena);
    }

}

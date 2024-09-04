package com.zunescape.arenas;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Tourneys {

    HashMap storeTourneys = new HashMap<String, String>();

    public String addTourney(String tourney) {
        storeTourneys.put(tourney, tourney.hashCode());

        return String.valueOf(tourney.hashCode());
    }

    public Boolean hasInvite(String invite) {
        Set<Map.Entry<String, String>> set = storeTourneys.entrySet();
        for (Map.Entry entry : set) {
            if (String.valueOf(entry.getValue()).equals(invite)) {
                return true;
            }
        }
        return false;
    }

    public String loadTourney(String inviteKey) {
        Set<Map.Entry<String, String>> set = storeTourneys.entrySet();
        for (Map.Entry<String, String> entry : set) {
            if (String.valueOf(entry.getValue()).equals(inviteKey)) {
                return entry.getKey();
            }
        }
        return "";
    }
}

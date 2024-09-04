package com.zunescape.arenas;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Arenas {

    public Set<String> arenas = new HashSet(Arrays.asList("ZobbleStone", "Zust 2", "Zubway Zurfers" ));

    public Set getArenas() {
        return arenas;
    }

    public void addArena(String arena) {
        arenas.add(arena);
    }

    public Boolean loadArena (String arena) {
        return true;
    }

}

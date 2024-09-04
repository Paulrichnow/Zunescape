package com.zunescape.royale.util;

import com.zunescape.royale.chat.ChatDecorator;

import java.util.Set;

public class RangeValidator implements ChatDecorator {

    public Set<Contestant> withinRange(Set<Contestant> contestants, Contestant primary) {

        return contestants;
    }

    @Override
    public void post(Set<Contestant> contestants, Contestant speaker, String msg) {
        for (Contestant contestant : withinRange(contestants, speaker)) {
            System.out.println(contestant.getUsername() +  ": Hello, World!");
        }
    }
}

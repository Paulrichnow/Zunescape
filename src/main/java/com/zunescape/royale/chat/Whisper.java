package com.zunescape.royale.chat;

import com.zunescape.royale.util.Contestant;

import java.util.Set;

public class Whisper implements ChatDecorator{

    @Override
    public void post(Set<Contestant> contestants, Contestant speaker, String msg) {
        System.out.println("Hello " + speaker.getUsername());
    }
}

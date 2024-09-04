package com.zunescape.royale.chat;

import com.zunescape.royale.util.Contestant;

import java.util.Set;

public interface ChatDecorator {

    void post(Set<Contestant> contestants, Contestant speaker, String msg);
}

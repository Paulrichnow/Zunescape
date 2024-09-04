package com.zunescape.royale.chat;

import com.zunescape.royale.util.Contestant;
import java.util.Set;

public class Chat {

    private ChatDecorator decorator;

    public Chat(ChatDecorator decorator) {
        this.decorator = decorator;
    }

    public void post (Set<Contestant> contestants, Contestant speaker, String msg) {
        decorator.post(contestants, speaker, msg);
    }

    public void setDecorator(ChatDecorator decorator) {
        this.decorator = decorator;
    }
}

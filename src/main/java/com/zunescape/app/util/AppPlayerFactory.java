package com.zunescape.app.util;

import com.zunescape.auth.AuthSession;
import com.zunescape.player.Player;
import com.zunescape.player.util.AbstractPlayerFactory;

public class AppPlayerFactory implements AbstractPlayerFactory{

    @Override
    public Player getPrimaryPlayer() {
        AuthSession session = AuthSession.getSession();
        Player player = session.auth();
        return player;
    }
}

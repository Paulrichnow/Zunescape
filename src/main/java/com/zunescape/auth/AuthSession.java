package com.zunescape.auth;

import com.zunescape.player.Player;

import java.util.Scanner;

public class AuthSession {

    String username;
    private static AuthSession session;

    public static AuthSession getSession() {
        if (session == null) {
            return new AuthSession();
        }
        else {
            return session;
        }
    }

    public Player auth() {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter preferred username:");
        username = scnr.next();
        return new Player(){

            @Override
            public String getPUID() {
                return String.valueOf(username.hashCode());
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public String toString() {
                return username;
            }
        };
    }
}

package com.tictactoe.game;

public class Player {

    private final char mark;

    public Player(char mark) {
        this.mark = mark;
    }

    protected char getMark() {
        return mark;
    }

}

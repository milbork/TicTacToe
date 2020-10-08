package com.tictactoe;

import com.tictactoe.game.Board;
import com.tictactoe.game.Game;
import com.tictactoe.game.Player;

public class StartGame {

    public static void main(String[] args) {
        Board board = new Board();
        Player playerX = new Player('X');
        Player playerO = new Player('O');
        Game game = new Game(0, board, playerX, playerO);
        game.play();
    }
}

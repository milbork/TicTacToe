package com.tictactoe.game;

public class Game {

    private int moves;
    private Board board;
    private Player playerX;
    private Player playerO;


    public Game(int moves, Board board, Player playerX, Player playerO) {
        this.moves = moves;
        this.board = board;
        this.playerX = playerX;
        this.playerO = playerO;
    }

    public Game() {
    }

    protected void setMoves(int value) {
        this.moves = value;
    }

    protected int getMoves() {
        return moves;
    }

    public void play() {
        do {
            playersMove(playerX);
            if (moves == -1) {
                break;
            }
            playersMove(playerO);
        } while (moves != -1);
        board.showBoard();
    }

    protected void playersMove(Player player) {
        board.showBoard();
        board.putMarkOnBoard(player);
        moves++;
        if (moves >= 5) {
            if (checkGameStatus(player)) {
                moves = -1;
            }
        }
    }

    protected boolean checkGameStatus(Player player) {
        if (checkWinStatus(player)) {
            System.out.println("Player " + player.getMark() + " wins!\n");
            return true;
        } else if (moves >= 9) {
            System.out.println("We have a tie!\n");
            return true;
        }
        return false;
    }

    protected boolean checkWinStatus(Player player) {
        return
                // A ROW
                (board.getBoard()[2][2] == player.getMark() &&
                        board.getBoard()[2][4] == player.getMark() &&
                        board.getBoard()[2][6] == player.getMark())
                        ||  // B ROW
                        (board.getBoard()[4][2] == player.getMark() &&
                                board.getBoard()[4][4] == player.getMark() &&
                                board.getBoard()[4][6] == player.getMark())
                        ||  // C ROW
                        ((board.getBoard()[6][2] == player.getMark() &&
                                board.getBoard()[6][4] == player.getMark() &&
                                board.getBoard()[6][6] == player.getMark()))
                        ||  // 1 COLUMN
                        ((board.getBoard()[2][2] == player.getMark() &&
                                board.getBoard()[4][2] == player.getMark() &&
                                board.getBoard()[6][2] == player.getMark()))
                        ||  // 2 COLUMN
                        ((board.getBoard()[2][4] == player.getMark() &&
                                board.getBoard()[4][4] == player.getMark() &&
                                board.getBoard()[6][4] == player.getMark()))
                        ||  // 3 COLUMN
                        ((board.getBoard()[2][6] == player.getMark() &&
                                board.getBoard()[4][6] == player.getMark() &&
                                board.getBoard()[6][6] == player.getMark()))
                        || // FORWARD AXIS
                        ((board.getBoard()[2][6] == player.getMark() &&
                                board.getBoard()[4][4] == player.getMark() &&
                                board.getBoard()[6][2] == player.getMark()))
                        ||  // BACKWARD AXIS
                        ((board.getBoard()[2][2] == player.getMark() &&
                                board.getBoard()[4][4] == player.getMark() &&
                                board.getBoard()[6][6] == player.getMark()));
    }

}
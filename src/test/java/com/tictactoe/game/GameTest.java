package com.tictactoe.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class GameTest {

    private Game game;
    private Player player1;
    private Player player2;
    private Board board;

    @BeforeEach
    void init() {
        player1 = new Player('X');
        player2 = new Player('O');
        board = mock(Board.class);
        game = new Game(0, board, player1, player1);
    }

    // playersMove method tests

    @Test
    void shouldIncreaseMovesValue() {

        doNothing().when(board).showBoard();
        doNothing().when(board).putMarkOnBoard(player1);

        game.playersMove(player1);
        Assertions.assertEquals(1, game.getMoves());

    }

    @Test
    void shouldSetNegativeMovesValueIfGameStatusIsTrue() {

        when(board.getBoard()).thenReturn(getBoardNotWin());
        doNothing().when(board).putMarkOnBoard(player1);

        game.setMoves(8);
        game.playersMove(player1);

        Assertions.assertEquals(-1, game.getMoves());

    }

    // checkGameStatus method tests

    @Test
    void shouldReturnTrueIfWinStatusIsTrue() {

        when(board.getBoard()).thenReturn(getBoardWin());
        Assertions.assertTrue(game.checkGameStatus(player1));
    }

    @Test
    void shouldReturnTrueIfMovesIsEqualToNine() {
        when(board.getBoard()).thenReturn(getBoardNotWin());
        game.setMoves(9);
        Assertions.assertTrue(game.checkGameStatus(player1));
    }

    @Test
    void shouldReturnFalseIfBothConditionsAreFalse() {

        when(board.getBoard()).thenReturn(getBoardNotWin());
        Assertions.assertFalse(game.checkGameStatus(player1));
    }

    //  checkWinStatus method tests

    @Test
    void shouldReturnTrueIfWinningConditionIsCompleted() {
        char[][] mockBoard1 = {
                {' ', '|', '1', '|', '2', '|', '3'},
                {'-', '+', '-', '+', '-', '+', '-'},
                {'A', '|', 'X', '|', '.', '|', '.'},
                {'-', '+', '-', '+', '-', '+', '-'},
                {'B', '|', 'X', '|', '.', '|', '.'},
                {'-', '+', '-', '+', '-', '+', '-'},
                {'C', '|', 'X', '|', '.', '|', '.'}
        };
        char[][] mockBoard2 = {
                {' ', '|', '1', '|', '2', '|', '3'},
                {'-', '+', '-', '+', '-', '+', '-'},
                {'A', '|', '.', '|', '.', '|', '.'},
                {'-', '+', '-', '+', '-', '+', '-'},
                {'B', '|', 'X', '|', 'X', '|', 'X'},
                {'-', '+', '-', '+', '-', '+', '-'},
                {'C', '|', '.', '|', '.', '|', '.'}
        };
        char[][] mockBoard3 = {
                {' ', '|', '1', '|', '2', '|', '3'},
                {'-', '+', '-', '+', '-', '+', '-'},
                {'A', '|', 'O', '|', '.', '|', '.'},
                {'-', '+', '-', '+', '-', '+', '-'},
                {'B', '|', '.', '|', 'O', '|', '.'},
                {'-', '+', '-', '+', '-', '+', '-'},
                {'C', '|', '.', '|', '.', '|', 'O'}
        };
        when(board.getBoard()).thenReturn(mockBoard1);
        Assertions.assertTrue(game.checkWinStatus(player1));
        when(board.getBoard()).thenReturn(mockBoard2);
        Assertions.assertTrue(game.checkWinStatus(player1));
        when(board.getBoard()).thenReturn(mockBoard3);
        Assertions.assertTrue(game.checkWinStatus(player2));
    }

    @Test
    void shouldReturnFalseIfWinningConditionIsNotCompleted() {

        when(board.getBoard()).thenReturn(getBoardNotWin());
        Assertions.assertFalse(game.checkWinStatus(player1));
        when(board.getBoard()).thenReturn(getBoardNotWin());
        Assertions.assertFalse(game.checkWinStatus(player1));
        when(board.getBoard()).thenReturn(getBoardNotWin());
        Assertions.assertFalse(game.checkWinStatus(player2));
    }

    private char[][] getBoardWin() {
        return new char[][]{
                {' ', '|', '1', '|', '2', '|', '3'},
                {'-', '+', '-', '+', '-', '+', '-'},
                {'A', '|', 'X', '|', '.', '|', '.'},
                {'-', '+', '-', '+', '-', '+', '-'},
                {'B', '|', 'X', '|', '.', '|', '.'},
                {'-', '+', '-', '+', '-', '+', '-'},
                {'C', '|', 'X', '|', '.', '|', '.'}
        };
    }

    private char[][] getBoardNotWin() {
        return new char[][]{
                {' ', '|', '1', '|', '2', '|', '3'},
                {'-', '+', '-', '+', '-', '+', '-'},
                {'A', '|', 'O', '|', '.', '|', '.'},
                {'-', '+', '-', '+', '-', '+', '-'},
                {'B', '|', 'X', '|', '.', '|', '.'},
                {'-', '+', '-', '+', '-', '+', '-'},
                {'C', '|', 'X', '|', '.', '|', '.'}
        };
    }
}
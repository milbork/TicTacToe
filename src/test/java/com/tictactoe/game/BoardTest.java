package com.tictactoe.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoardTest {
    private Board board;
    private Player player;

    @BeforeEach
    void init() {
        board = spy(Board.class);
        player = new Player('X');
    }

    //  showBoard method tests

    @Test
    void shouldPassIfPrintedArraysAreEqual() {

        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String givenBoard =
                " |1|2|3\n" +
                        "-+-+-+-\n" +
                        "A|.|.|.\n" +
                        "-+-+-+-\n" +
                        "B|.|.|.\n" +
                        "-+-+-+-\n" +
                        "C|.|.|.\n";

        board.showBoard();

        Assertions.assertEquals(givenBoard, outputStreamCaptor.toString());
        System.setOut(standardOut);
    }

    //  putMarkOnBoard method test

    @Test
    void shouldPutMarkOnBoardInCorrectPlace() {
        char[][] mockBoard = {
                {' ', '|', '1', '|', '2', '|', '3'},
                {'-', '+', '-', '+', '-', '+', '-'},
                {'A', '|', 'X', '|', '.', '|', '.'},
                {'-', '+', '-', '+', '-', '+', '-'},
                {'B', '|', '.', '|', '.', '|', '.'},
                {'-', '+', '-', '+', '-', '+', '-'},
                {'C', '|', '.', '|', '.', '|', '.'}
        };

        doReturn(true).when(board).checkIfCoordinatesAreCorrect("A1");
        doReturn(true).when(board).checkIfPlaceOccupied(2, 2);
        doReturn("A1").when(board).userInput();

        board.putMarkOnBoard(player);

        Assertions.assertArrayEquals(board.getBoard(), mockBoard);

    }

    // checkIfPlaceOccupied method tests

    @Test
    void shouldReturnTrueIfPlaceOnBoardIsNotOccupied() {
        Assertions.assertTrue(board.checkIfPlaceOccupied(2, 2));
    }

    @Test
    void shouldReturnFalseIfPlaceOnBoardIsOccupied() {
        Assertions.assertFalse(board.checkIfPlaceOccupied(2, 1));
    }

    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfCoordinatesAreWrong() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> board.checkIfPlaceOccupied(2, 20));
    }

    // checkIfCoordinatesAreCorrect method tests

    @Test
    void shouldReturnTrueIfCoordinatesAreCorrect() {
        Assertions.assertAll(
                () -> assertTrue(board.checkIfCoordinatesAreCorrect("a1")),
                () -> assertTrue(board.checkIfCoordinatesAreCorrect("A2")),
                () -> assertTrue(board.checkIfCoordinatesAreCorrect("a3")),
                () -> assertTrue(board.checkIfCoordinatesAreCorrect("b1")),
                () -> assertTrue(board.checkIfCoordinatesAreCorrect("B2")),
                () -> assertTrue(board.checkIfCoordinatesAreCorrect("B3")),
                () -> assertTrue(board.checkIfCoordinatesAreCorrect("c1")),
                () -> assertTrue(board.checkIfCoordinatesAreCorrect("c2")),
                () -> assertTrue(board.checkIfCoordinatesAreCorrect("C3"))
        );

    }

    @Test
    void shouldReturnFalseIfCoordinatesAreIncorrect() {

        Assertions.assertAll(
                () -> assertFalse(board.checkIfCoordinatesAreCorrect("a12")),
                () -> assertFalse(board.checkIfCoordinatesAreCorrect("1A")),
                () -> assertFalse(board.checkIfCoordinatesAreCorrect("G3")),
                () -> assertFalse(board.checkIfCoordinatesAreCorrect("AB")),
                () -> assertFalse(board.checkIfCoordinatesAreCorrect("C0")),
                () -> assertFalse(board.checkIfCoordinatesAreCorrect("B4"))
        );
    }

    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfSpecialSignUsed() {
        Assertions.assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class, () -> board.checkIfCoordinatesAreCorrect(" ")),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> board.checkIfCoordinatesAreCorrect("*")),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> board.checkIfCoordinatesAreCorrect("#")),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> board.checkIfCoordinatesAreCorrect("."))
        );
    }
}
package com.tictactoe.game;

import java.util.Scanner;

public class Board {

    private final char[][] board = {
            {' ', '|', '1', '|', '2', '|', '3'},
            {'-', '+', '-', '+', '-', '+', '-'},
            {'A', '|', '.', '|', '.', '|', '.'},
            {'-', '+', '-', '+', '-', '+', '-'},
            {'B', '|', '.', '|', '.', '|', '.'},
            {'-', '+', '-', '+', '-', '+', '-'},
            {'C', '|', '.', '|', '.', '|', '.'}
    };
    private int fuse = 50;

    public Board() {
    }

    protected char[][] getBoard() {
        return board;
    }

    protected void showBoard() {
        for (char[] fullBoard : board) {
            for (char line : fullBoard) {
                System.out.print(line);
            }
            System.out.println();
        }
    }

    protected void putMarkOnBoard(Player player) {

        try {
            System.out.println("\nPlayer " +player.getMark()+"\nChose your place on the board: \n");

            String coordinate = userInput();

            int x;
            int y;

            if (checkIfCoordinatesAreCorrect(coordinate)) {
                y = Character.getNumericValue(coordinate.charAt(1)) * 2;
                switch (coordinate.toUpperCase().charAt(0)) {
                    case 'A':
                        x = 2;
                        break;
                    case 'B':
                        x = 4;
                        break;
                    case 'C':
                        x = 6;
                        break;
                    default:
                        throw new IllegalArgumentException();
                }

                if (checkIfPlaceOccupied(x, y)) {
                    board[x][y] = player.getMark();
                } else {
                    throw new IllegalArgumentException();
                }
            } else {
                throw new IllegalArgumentException();
            }

        } catch (IndexOutOfBoundsException | IllegalArgumentException exc) {

            if (fuse != 0) {
                --fuse;
                showBoard();
                System.out.println("You choose wrong coordinates, try again!\n");
                putMarkOnBoard(player);
            } else {
                System.out.println("You choose to many wrong coordinates, start game again!");
                System.exit(0);
            }
        }
    }

    protected boolean checkIfCoordinatesAreCorrect(String coordinate) throws IndexOutOfBoundsException {

        char firstCharacter = coordinate.toUpperCase().charAt(0);
        int secondCharacter = Character.getNumericValue(coordinate.charAt(1));

        return coordinate.length() <= 2
                && Character.isLetter(firstCharacter)
                && (firstCharacter == 'A' || firstCharacter == 'B' || firstCharacter == 'C')
                && Character.isDigit(coordinate.charAt(1))
                && secondCharacter != 0
                && secondCharacter <= 3;
    }

    protected boolean checkIfPlaceOccupied(int x, int y) throws IndexOutOfBoundsException {
        return board[x][y] == '.';
    }

    protected String userInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
package com.example.tic_tok_toe.models;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Scanner;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player implements Serializable {

    private char Symbol;

    private String name;

    private PlayerType playerType;
    public Move decideMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the row to make a move : ");
        int row = scanner.nextInt();

        System.out.println("Enter the col to make a move : ");
        int col = scanner.nextInt();

        return new Move(this, new Cell(row, col));
    }
}

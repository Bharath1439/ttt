package com.example.tic_tok_toe.models;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cell implements Serializable {
    private Player player;

    private int row;

    private int col;

    private CellState cellState;

    public Cell(int i, int j) {
        this.row=i;
        this.col=j;
    }
}

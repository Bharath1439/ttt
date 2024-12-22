package com.example.tic_tok_toe.stratergy;

import com.example.tic_tok_toe.models.Board;
import com.example.tic_tok_toe.models.Move;

public interface GameWinningStrategy {
    boolean checkWinner(Board board, Move move);

}

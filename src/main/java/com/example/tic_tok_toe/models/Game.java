package com.example.tic_tok_toe.models;

import com.example.tic_tok_toe.exceptions.InvalidException;
import com.example.tic_tok_toe.stratergy.GameWinningStrategy;
import com.example.tic_tok_toe.stratergy.OrderOneGameWinningStrategy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game implements Serializable {

    private Board board;

    private List<Player> players;

    private List<Move> moves;

    private GameStatus gameStatus;

    private int nextPlayerIndex;

    private Player winningPlayer;

    private GameWinningStrategy gameWinningStrategy;


    public static Builder getBuilder() {
        return new Builder();
    }
    public void makeNextMove() {
        Player playerWhoMoveItis = players.get(nextPlayerIndex);
        System.out.println("It is " +
                playerWhoMoveItis.getName() + "'s turn");

        Move move = playerWhoMoveItis.decideMove(board);

        int row = move.getCel().getRow();
        int col = move.getCel().getCol();

        if (board.getBoard().get(row).get(col).getCellState()
                .equals(CellState.EMPTY)) {
            board.applyMove(move);
            moves.add(move);

            // check winner
            if (gameWinningStrategy.checkWinner(board, move)) {
                gameStatus = GameStatus.ENDED;
                winningPlayer = playerWhoMoveItis;
            }

            // DRAW
            if (moves.size() == board.getSize() * board.getSize()) {
                gameStatus = GameStatus.DRAW;
            }

            nextPlayerIndex += 1;
            nextPlayerIndex %= players.size();
        } else {
            // throw an exception
        }
    }


        public static class Builder {
        private int dimension;
        private List<Player> players;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Game build() {
            // validation
            try {
                isValid();
            } catch (InvalidException e) {
                return null;
            }

            Game game = new Game();
            Board board1=new Board();
            board1.setSize(dimension);
            game.setBoard(board1);
            game.setPlayers(players);
            game.setMoves(new LinkedList<>());
            game.setNextPlayerIndex(0);
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setGameWinningStrategy(
                    new OrderOneGameWinningStrategy(dimension));

            return game;
        }

        private boolean isValid() throws InvalidException {
            if(dimension < 3) {
                //return false;
                throw new InvalidException
                        ("Dimension should be greater than 2");
            }
            // TODO to add more validation

            return true;
        }
    }


}

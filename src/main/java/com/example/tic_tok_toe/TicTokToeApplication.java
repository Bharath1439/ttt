package com.example.tic_tok_toe;

import com.example.tic_tok_toe.controller.GameController;
import com.example.tic_tok_toe.models.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class TicTokToeApplication {

	public static void main(String[] args) {

//		SpringApplication.run(TicTokToeApplication.class, args);
		System.out.println("Hello world! Welcome to Tictactoe");
		Scanner scanner = new Scanner(System.in);

		System.out.println("What is the dimension of the game : ");
		int dimension = scanner.nextInt();

		System.out.println("No of Players: ");
		int noOfPlayers = scanner.nextInt();

		List<Player> players = new LinkedList<>();

		System.out.println("Will there be any bot ? y/n");
		String isBot = scanner.next();

		if(isBot.equals("y")) {
			noOfPlayers = noOfPlayers - 1;

			System.out.println("Enter the name of the Bot: ");
			String name = scanner.next();

			System.out.println("Enter the symbol of the Bot: ");
			String botSymbol = scanner.next();

			System.out.println("Enter Bot Diffculty level: 1-Easy 2-Med 3-Hard");
			int diffcultyLevel = scanner.nextInt();

			//todo for value to enum
			Bot bot=new Bot();
			bot.setBotDifficultyLevel(BotDifficultyLevel.EASY);
			bot.setSymbol(botSymbol.charAt(0));
			bot.setName(name);
			players.add(new Bot());
		}

		for(int i = 0; i < noOfPlayers; i++) {
			System.out.println("What is the name of the player: " + (i+1));
			String name = scanner.next();

			System.out.println("What is the symbol for player: " + (i+1));
			String symbol = scanner.next(); //Assumption : Single character.

			Player player = new Player(symbol.charAt(0), name, PlayerType.HUMAN);
			players.add(player);
		}
		GameController gameController = new GameController();

		Game game = gameController.createGame(dimension, players);
		while(gameController.getGameStatus(game)
				== GameStatus.IN_PROGRESS) {
			// TODO play the game
			//break;
			System.out.println("Current Board: ");
			gameController.displayBoard(game);

			gameController.executeNextMove(game);
		}

		if(gameController.getGameStatus(game) == GameStatus.DRAW) {
			System.out.println("Game has drawn");
		} else {
			System.out.println("Game has won by: "
					+ gameController.getWinnerName(game));
		}
    	}


}

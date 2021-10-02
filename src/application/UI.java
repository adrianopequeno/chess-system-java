package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	// M�todo que ler uma posicao no xadrez
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Erro ao instanciar ChessPostition. os valores v�lidos v�o de a1 a a8.");
		}
	}

	// M�todo que retorno o turno do jogador
	public static void printMatch(ChessMatch chessMath, List<ChessPiece> captured) {
		printBoard(chessMath.getPieces());
		System.out.println();
		printCapturePieces(captured);
		System.out.println();
		System.out.println("Turno: " + chessMath.getTurn());
		System.out.println("Esperando o Jogador: " + chessMath.getCurrentPlayer());
		
		if (chessMath.getCheck()) System.out.println("CHECK!");
	}

	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	// metodo que imprime uma pe�a de xadres com cor
	public static void printPiece(ChessPiece piece, boolean background) {

		// muda a cor de fundo da tela
		if (background)
			System.out.print(ANSI_BLUE_BACKGROUND);

		if (piece == null)
			System.out.print("-" + ANSI_RESET);
		else {
			if (piece.getColor() == Color.WHITE)
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			else
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
		}
		System.out.print(" ");
	}

	// M�todo responsavel por imprimir a lista de pe�as capturadas
	private static void printCapturePieces(List<ChessPiece> captured) {
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE)
				.collect(Collectors.toList()); // filtra todas as pe�as brancas
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK)
				.collect(Collectors.toList()); // filtra todas as pe�as pretas
		
		System.out.println("Pe�as capturadas:");
		System.out.print("Brancas: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));// pradrao de imprimir arrays no java
		System.out.print(ANSI_RESET);
		System.out.print("Pretas: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(black.toArray()));// pradrao de imprimir arrays no java
		System.out.print(ANSI_RESET);
	}

}

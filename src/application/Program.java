package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ChessMatch chessMatch = new ChessMatch();

		while (true) {
			try {
				UI.clearScreen(); // limpa a tela
				UI.printBoard(chessMatch.getPieces());
				System.out.println();
				System.out.print("Source: "); // entrando com a posicao de origem
				ChessPosition source = UI.readChessPosition(sc);

				System.out.println();
				System.out.print("Target: "); // entrando com a posicao de destino
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target); // movendo a pe�a da origem para
																						// o destino
			} catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}

}

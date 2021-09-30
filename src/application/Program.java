package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ChessMatch chessMatch = new ChessMatch();
		
		while (true) {
			UI.printBoard(chessMatch.getPieces());
			System.out.println();
			System.out.print("Source: "); // entrando com a posicao de origem
			ChessPosition source = UI.readChessPosition(sc);
			
			System.out.println();
			System.out.print("Target: "); // entrando com a posicao de destino
			ChessPosition target = UI.readChessPosition(sc);
			
			ChessPiece capturedPiece = chessMatch.performChessMove(source, target); // movendo a peça da origem para o destino
		}
	}

}

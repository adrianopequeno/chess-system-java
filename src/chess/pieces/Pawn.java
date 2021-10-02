package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getColumns()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// movimentos para a cor branca
		if (getColor() == Color.WHITE) {
			// movimenta o peao 'so uma casa
			p.setValues(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiace(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			// Caso seja a primeira vez do movimento do peao, ele pode mover-se duas casa
			p.setValues(position.getRow() - 2, position.getColumn());
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiace(p) && getBoard().positionExists(p2)
					&& !getBoard().thereIsAPiace(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// teste na diagonal esquerda. caso exista um peao adversario uma casa a frente
			// na diagonal
			// o peao pode mover-se para capturar a pe�a adversaria.
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// teste na diagonal direita. caso exista um peao adversario uma casa a frente
			// na diagonal
			// o peao pode mover-se para capturar a pe�a adversaria.
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		} else { // movimentos para a cor preta
			// movimenta o peao 'so uma casa
			p.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiace(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			// Caso seja a primeira vez do movimento do peao, ele pode mover-se duas casa
			p.setValues(position.getRow() + 2, position.getColumn());
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiace(p) && getBoard().positionExists(p2)
					&& !getBoard().thereIsAPiace(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// teste na diagonal esquerda. caso exista um peao adversario uma casa a frente
			// na diagonal
			// o peao pode mover-se para capturar a pe�a adversaria.
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// teste na diagonal direita. caso exista um peao adversario uma casa a frente
			// na diagonal
			// o peao pode mover-se para capturar a pe�a adversaria.
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}

		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}

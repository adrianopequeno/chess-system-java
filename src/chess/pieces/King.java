package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "K";
	}

	// Método auxiliar para saber se o rei pode se mover para uma dada posiçao
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	// Metodo que verifica se o torre pode fazer o movimento CASTLING com o rei
	private boolean testRookCasling(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

	// Método que verifica os movimentos possiveis ddo Rei
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getColumns()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// above
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p))
			mat[p.getRow()][p.getColumn()] = true;

		// below
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p))
			mat[p.getRow()][p.getColumn()] = true;

		// left
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p))
			mat[p.getRow()][p.getColumn()] = true;

		// right
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p))
			mat[p.getRow()][p.getColumn()] = true;

		// nw
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p))
			mat[p.getRow()][p.getColumn()] = true;

		// ne
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p))
			mat[p.getRow()][p.getColumn()] = true;

		// sw
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p))
			mat[p.getRow()][p.getColumn()] = true;

		// se
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p))
			mat[p.getRow()][p.getColumn()] = true;

		// Movimento especial CASTLING
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			// movimento especial roque torre do lado do rei :: ROQUE PEQUENO
			Position posT1 = new Position(position.getRow(), position.getColumn() + 3);
			if (testRookCasling(posT1)) {
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null)
					mat[position.getRow()][position.getColumn() + 2] = true;
			}
			// movimento especial roque torre do lado da rainha :: ROQUE GRANDE
			Position posT2 = new Position(position.getRow(), position.getColumn() - 4);
			if (testRookCasling(posT2)) {
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null)
					mat[position.getRow()][position.getColumn() - 2] = true;
			}
		}

		return mat;
	}
}

package boardgame;

public abstract class Piece {
	
	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	// método que testa se uma dada posiçao é possivel
	// Hook metod
	public Boolean possivelMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	// método que verifica se existe pelo menos um movimento possivel ou a peça esta travada, sem movimentos
	public Boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j]) return true;
			}
		}
		return false;
	}
}

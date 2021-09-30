package boardgame;

public class Board {

	private Integer rows;
	private Integer columns;
	private Piece[][] pieces;

	public Board(Integer rows, Integer columns) {
		if (rows < 1 || columns < 1)
			throw new BoardException("Erro ao criar o tabuleiro do jogo: deve haver pelo menos 1 linha e 1 coluna");

		this.rows = rows;
		this.columns = columns;

		pieces = new Piece[rows][columns];
	}

	public Integer getRows() {
		return rows;
	}

	public Integer getColumns() {
		return columns;
	}

	public Piece piece(Integer row, Integer column) {
		if (!positionExistsRowColumn(row, column))
			throw new BoardException("A posi��o n�o est� no tabuleiro!");
		return pieces[row][column];
	}

	// sobrecarga do m�tod piece
	public Piece piece(Position position) {
		if (!positionExists(position))
			throw new BoardException("A posi��o n�o est� no tabuleiro!");

		return pieces[position.getRow()][position.getColumn()];
	}

	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiace(position))
			throw new BoardException("J� existe uma pe�a na posi��o " + position);

		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

	// M�todo que remove uma pe�a
	public Piece removePiece(Position position) {
		if (!positionExists(position))
			throw new BoardException("A posi��o n�o est� no tabuleiro!");

		if (piece(position) == null)
			return null;

		Piece aux = piece(position); // pega a pe�a
		aux.position = null; // remove a pe�a do tabuleiro

		pieces[position.getRow()][position.getColumn()] = null; // indica que nao existe mais pe�a nessa posicao

		return aux;
	}

	// M�todo auxiliar para saber se uma posicao existe passando a linha e coluna
	private Boolean positionExistsRowColumn(Integer row, Integer column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	public Boolean positionExists(Position position) {
		return positionExistsRowColumn(position.getRow(), position.getColumn());
	}

	// metodo que verifica se existe uma pe�a numa dada posi�ao
	public Boolean thereIsAPiace(Position position) {
		if (!positionExists(position))
			throw new BoardException("A posi��o n�o est� no tabuleiro!");

		return piece(position) != null;
	}
}

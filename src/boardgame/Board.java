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
			throw new BoardException("A posição não está no tabuleiro!");
		return pieces[row][column];
	}

	// sobrecarga do métod piece
	public Piece piece(Position position) {
		if (!positionExists(position))
			throw new BoardException("A posição não está no tabuleiro!");

		return pieces[position.getRow()][position.getColumn()];
	}

	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiace(position))
			throw new BoardException("Já existe uma peça na posição " + position);

		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

	// Método que remove uma peça
	public Piece removePiece(Position position) {
		if (!positionExists(position))
			throw new BoardException("A posição não está no tabuleiro!");

		if (piece(position) == null)
			return null;

		Piece aux = piece(position); // pega a peça
		aux.position = null; // remove a peça do tabuleiro

		pieces[position.getRow()][position.getColumn()] = null; // indica que nao existe mais peça nessa posicao

		return aux;
	}

	// Método auxiliar para saber se uma posicao existe passando a linha e coluna
	private Boolean positionExistsRowColumn(Integer row, Integer column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	public Boolean positionExists(Position position) {
		return positionExistsRowColumn(position.getRow(), position.getColumn());
	}

	// metodo que verifica se existe uma peça numa dada posiçao
	public Boolean thereIsAPiace(Position position) {
		if (!positionExists(position))
			throw new BoardException("A posição não está no tabuleiro!");

		return piece(position) != null;
	}
}

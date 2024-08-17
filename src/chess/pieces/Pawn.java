package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumms()];

		Position p = new Position(0, 0);

		if (getColor() == Color.WHITE) {
			p.setValues(position.getRow() - 1, position.getColumm());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumm()] = true;
			}
			p.setValues(position.getRow() - 2, position.getColumm());
			Position p2 = new Position(position.getRow() - 1, position.getColumm());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2)
					&& !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumm()] = true;
			}
			p.setValues(position.getRow() - 1, position.getColumm() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumm()] = true;
			}
			p.setValues(position.getRow() - 1, position.getColumm() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumm()] = true;
			}

			// #specialmove en passant white
			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumm() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == chessMatch.getEnPassanVulnerable()) {
					mat[left.getRow() - 1][left.getColumm()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumm() + 1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right)
						&& getBoard().piece(right) == chessMatch.getEnPassanVulnerable()) {
					mat[right.getRow() - 1][right.getColumm()] = true;
				}
			}

		} else {
			p.setValues(position.getRow() + 1, position.getColumm());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumm()] = true;
			}
			p.setValues(position.getRow() + 2, position.getColumm());
			Position p2 = new Position(position.getRow() + 1, position.getColumm());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2)
					&& !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumm()] = true;
			}
			p.setValues(position.getRow() + 1, position.getColumm() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumm()] = true;
			}
			p.setValues(position.getRow() + 1, position.getColumm() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumm()] = true;
			}

			// #special en passant black
			if (position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumm() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == chessMatch.getEnPassanVulnerable()) {
					mat[left.getRow() + 1][left.getColumm()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumm() + 1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right)
						&& getBoard().piece(right) == chessMatch.getEnPassanVulnerable()) {
					mat[right.getRow() + 1][right.getColumm()] = true;
				}
			}
		}

		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}

}

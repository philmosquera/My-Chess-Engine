package movegen;

import java.util.ArrayList;

import main.GameLoop;
import bitboards.BlackPieces;
import bitboards.WhitePieces;
import board.BoardManager;
import board.FullGameState;

public class MoveGenerator {

	/*
	 * NOTE: Do not return a ArrayList<FullGameState> return squares numbers
	 * instead.
	 */
	public static ArrayList<FullGameState> generateWhiteLegalMoves(
			FullGameState currentGameState) {

		ArrayList<FullGameState> possibleStates = new ArrayList<FullGameState>();
		long temp;

		temp = currentGameState.getWhitePawns();
		while (temp != 0) {
			long nextPawn = Long.highestOneBit(temp);
			long bitboardOfMoves = WhitePieces.getPawnMoves(nextPawn,
					currentGameState.getOccupiedSquares(),
					currentGameState.getBlackPieces(),
					currentGameState.getEnPassantSquare());
			possibleStates.addAll(WhitePieceMoves.whitePawnMoves(nextPawn,
					bitboardOfMoves, currentGameState));

			temp = Long.highestOneBit(temp) ^ temp;
		}

		temp = currentGameState.getWhiteKnights();
		while (temp != 0) {

			long nextKnight = Long.highestOneBit(temp);
			long bitboardOfMoves = WhitePieces.getKnightMoves(nextKnight,
					currentGameState.getWhitePieces());
			possibleStates.addAll(WhitePieceMoves.whiteKnightMoves(nextKnight,
					bitboardOfMoves, currentGameState));

			temp = Long.highestOneBit(temp) ^ temp;
		}

		temp = currentGameState.getWhiteBishops();
		while (temp != 0) {
			long nextBishop = Long.highestOneBit(temp);
			long bitboardOfMoves = WhitePieces.getBishopMoves(nextBishop,
					currentGameState.getOccupiedSquares(),
					currentGameState.getWhitePieces());
			possibleStates.addAll(WhitePieceMoves.whiteBishopMoves(nextBishop,
					bitboardOfMoves, currentGameState));

			temp = Long.highestOneBit(temp) ^ temp;
		}

		temp = currentGameState.getWhiteQueens();
		while (temp != 0) {
			long nextQueen = Long.highestOneBit(temp);
			long bitboardOfMoves = WhitePieces.getQueenMoves(nextQueen,
					currentGameState.getOccupiedSquares(),
					currentGameState.getWhitePieces());
			possibleStates.addAll(WhitePieceMoves.whiteQueenMoves(nextQueen,
					bitboardOfMoves, currentGameState));

			temp = Long.highestOneBit(temp) ^ temp;
		}

		temp = currentGameState.getWhiteRooks();
		while (temp != 0) {
			long nextRook = Long.highestOneBit(temp);
			long bitboardOfMoves = WhitePieces.getRookMoves(nextRook,
					currentGameState.getOccupiedSquares(),
					currentGameState.getWhitePieces());
			possibleStates.addAll(WhitePieceMoves.whiteRookMoves(nextRook,
					bitboardOfMoves, currentGameState));

			temp = Long.highestOneBit(temp) ^ temp;
		}

		long kingMovesBitboard = WhitePieces.getKingMoves(
				currentGameState.getWhiteKing(),
				currentGameState.getWhitePieces(),
				currentGameState.getBlackAttackingSquares(),
				currentGameState.getWhiteCastleKing(),
				currentGameState.getWhiteCastleQueen(),
				currentGameState.getBlackPieces());
		possibleStates.addAll(WhitePieceMoves.whiteKingMoves(
				currentGameState.getWhiteKing(), kingMovesBitboard,
				currentGameState));

		return possibleStates;
	}

	public static ArrayList<FullGameState> generateBlackLegalMoves(
			FullGameState currentGameState) {

		ArrayList<FullGameState> possibleStates = new ArrayList<FullGameState>();
		long temp;

		temp = currentGameState.getBlackPawns();
		while (temp != 0) {

			long nextPawn = Long.highestOneBit(temp);
			long bitboardOfMoves = BlackPieces.getPawnMoves(nextPawn,
					currentGameState.getOccupiedSquares(),
					currentGameState.getWhitePieces(),
					currentGameState.getEnPassantSquare());
			possibleStates.addAll(BlackPieceMoves.BlackPawnMoves(nextPawn,
					bitboardOfMoves, currentGameState));

			temp = Long.highestOneBit(temp) ^ temp;
		}

		temp = currentGameState.getBlackKnights();
		while (temp != 0) {

			long nextKnight = Long.highestOneBit(temp);
			long bitboardOfMoves = BlackPieces.getKnightMoves(nextKnight,
					currentGameState.getBlackPieces());
			possibleStates.addAll(BlackPieceMoves.BlackKnightMoves(nextKnight,
					bitboardOfMoves, currentGameState));

			temp = Long.highestOneBit(temp) ^ temp;
		}

		temp = currentGameState.getBlackBishops();
		while (temp != 0) {
			long nextBishop = Long.highestOneBit(temp);
			long bitboardOfMoves = BlackPieces.getBishopMoves(nextBishop,
					currentGameState.getOccupiedSquares(),
					currentGameState.getBlackPieces());
			possibleStates.addAll(BlackPieceMoves.BlackBishopMoves(nextBishop,
					bitboardOfMoves, currentGameState));

			temp = Long.highestOneBit(temp) ^ temp;
		}

		temp = currentGameState.getBlackQueens();
		while (temp != 0) {
			long nextQueen = Long.highestOneBit(temp);
			long bitboardOfMoves = BlackPieces.getQueenMoves(nextQueen,
					currentGameState.getOccupiedSquares(),
					currentGameState.getBlackPieces());
			possibleStates.addAll(BlackPieceMoves.BlackQueenMoves(nextQueen,
					bitboardOfMoves, currentGameState));

			temp = Long.highestOneBit(temp) ^ temp;
		}

		temp = currentGameState.getBlackRooks();
		while (temp != 0) {
			long nextRook = Long.highestOneBit(temp);
			long bitboardOfMoves = BlackPieces.getRookMoves(nextRook,
					currentGameState.getOccupiedSquares(),
					currentGameState.getBlackPieces());
			possibleStates.addAll(BlackPieceMoves.BlackRookMoves(nextRook,
					bitboardOfMoves, currentGameState));

			temp = Long.highestOneBit(temp) ^ temp;
		}

		long kingMovesBitboard = BlackPieces.getKingMoves(
				currentGameState.getBlackKing(),
				currentGameState.getBlackPieces(),
				currentGameState.getWhiteAttackingSquares(),
				currentGameState.getBlackCastleKing(),
				currentGameState.getBlackCastleQueen(),
				currentGameState.getWhitePieces());
		possibleStates.addAll(BlackPieceMoves.BlackKingMoves(
				currentGameState.getBlackKing(), kingMovesBitboard,
				currentGameState));

		return possibleStates;
	}

	private static void printBitboard(long bitBoard) {
		String stringBitBoard = Long.toBinaryString(bitBoard);
		System.out.println("Value : " + stringBitBoard);
		while (stringBitBoard.length() != 64) {
			stringBitBoard = "0" + stringBitBoard;
		}

		for (int i = 0; i < 8; i++) {
			StringBuilder stringReverser = new StringBuilder(
					stringBitBoard.substring(i * 8, ((i + 1) * 8)));
			stringReverser.reverse();
			for (int j = 0; j < stringReverser.toString().length(); j++) {
				System.out.print(stringReverser.toString().charAt(j) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	/*
	 * Temporary class for printing out the current state of the board wihout
	 * relying on a gui.
	 */
	public static void printBoard(char[][] board) {
		for (int y = 7; y >= 0; y--) {
			for (int x = 0; x < board.length; x++) {
				char temp = board[x][y];
				if (temp == ' ') {
					System.out.print(", ");
				} else {
					System.out.print(board[x][y] + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
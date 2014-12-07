package board;

import java.util.Vector;

public class MoveGenerator {

	public static int count = 0;

	/*
	 * NOTE: Do not return a Vector<String[][]> return squares numbers instead.
	 */
	protected static Vector<String[][]> generateWhiteLegalMoves(
			String[][] currentBoard, long whitePawns, long whiteRooks,
			long whiteKnights, long whiteBishops, long whiteQueens,
			long whiteKing, long blackPieces, long whitePieces,
			long blackAttackingSquares) {

		Vector<String[][]> possibleStates = new Vector<String[][]>(20, 20);

		while (whitePawns != 0) {

			long nextPawn = Long.highestOneBit(whitePawns);
			// System.out.println("The next piece is: ");
			// printBitboard(nextPawn);
			// System.out.println("With possible moves: ");
			long bitboardOfMoves = WhitePieces.getPawnMoves(nextPawn,
					whitePieces | blackPieces, blackPieces);
			// printBitboard(bitboardOfMoves);

			possibleStates.addAll(generateNextMoves(nextPawn, bitboardOfMoves,
					copyCurrentBoard(currentBoard)));

			String whitePawnsString = Long.toBinaryString(whitePawns);
			// stops String.substring(1) throwing an error
			if (whitePawnsString.length() == 1) {
				break;
			}
			whitePawns = Long.parseLong(whitePawnsString.substring(1), 2);
		}

		while (whiteKnights != 0) {

			long nextKnight = Long.highestOneBit(whiteKnights);
			// System.out.println("The next piece is: ");
			// printBitboard(nextPawn);
			// System.out.println("With possible moves: ");
			long bitboardOfMoves = WhitePieces.getKnightMoves(nextKnight,
					whitePieces);
			// printBitboard(bitboardOfMoves);

			possibleStates.addAll(generateNextMoves(nextKnight,
					bitboardOfMoves, copyCurrentBoard(currentBoard)));

			String whiteKnightsString = Long.toBinaryString(whiteKnights);
			// stops String.substring(1) throwing an error
			if (whiteKnightsString.length() == 1) {
				break;
			}
			whiteKnights = Long.parseLong(whiteKnightsString.substring(1), 2);
		}

		while (whiteBishops != 0) {
			long nextBishop = Long.highestOneBit(whiteBishops);
			// System.out.println("The next piece is: ");
			// printBitboard(nextPawn);
			// System.out.println("With possible moves: ");
			long bitboardOfMoves = WhitePieces.getBishopMoves(nextBishop,
					whitePieces | blackPieces, whitePieces);
			// printBitboard(bitboardOfMoves);

			possibleStates.addAll(generateNextMoves(nextBishop,
					bitboardOfMoves, copyCurrentBoard(currentBoard)));

			String whiteBishopsString = Long.toBinaryString(whiteBishops);
			// stops String.substring(1) throwing an error
			if (whiteBishopsString.length() == 1) {
				break;
			}
			whiteBishops = Long.parseLong(whiteBishopsString.substring(1), 2);
		}

		while (whiteQueens != 0) {
			long nextQueen = Long.highestOneBit(whiteQueens);
			// System.out.println("The next piece is: ");
			// printBitboard(nextPawn);
			// System.out.println("With possible moves: ");
			long bitboardOfMoves = WhitePieces.getQueenMoves(whiteQueens,
					whitePieces | blackPieces, whitePieces);
			// printBitboard(bitboardOfMoves);

			possibleStates.addAll(generateNextMoves(nextQueen, bitboardOfMoves,
					copyCurrentBoard(currentBoard)));

			String whiteQueensString = Long.toBinaryString(whiteQueens);
			// stops String.substring(1) throwing an error
			if (whiteQueensString.length() == 1) {
				break;
			}
			whiteQueens = Long.parseLong(whiteQueensString.substring(1), 2);
		}

		while (whiteRooks != 0) {
			long nextRook = Long.highestOneBit(whiteRooks);
			// System.out.println("The next piece is: ");
			// printBitboard(nextPawn);
			// System.out.println("With possible moves: ");
			long bitboardOfMoves = WhitePieces.getQueenMoves(nextRook,
					whitePieces | blackPieces, whitePieces);
			// printBitboard(bitboardOfMoves);

			possibleStates.addAll(generateNextMoves(nextRook, bitboardOfMoves,
					copyCurrentBoard(currentBoard)));

			String whiteRooksString = Long.toBinaryString(whiteRooks);
			// stops String.substring(1) throwing an error
			if (whiteRooksString.length() == 1) {
				break;
			}
			whiteRooks = Long.parseLong(whiteRooksString.substring(1), 2);
		}

		long kingMovesBitboard = WhitePieces.getKingMoves(whiteKing,
				whitePieces, blackAttackingSquares);
		possibleStates.addAll(generateNextMoves(whiteKing, kingMovesBitboard,
				currentBoard));

		if (possibleStates.isEmpty()) {
			System.out.println("Checkmate!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
		return possibleStates;
	}

	protected static Vector<String[][]> generateBlackLegalMoves(
			String[][] currentBoard, long blackPawns, long blackRooks,
			long blackKnights, long blackBishops, long blackQueens,
			long blackKing, long whitePieces, long blackPieces,
			long whiteAttackingSquares) {

		Vector<String[][]> possibleStates = new Vector<String[][]>(20, 20);

		while (blackPawns != 0) {
			long nextPawn = Long.highestOneBit(blackPawns);
			// System.out.println("The next piece is: ");
			// printBitboard(nextPawn);
			// System.out.println("With possible moves: ");
			long bitboardOfMoves = BlackPieces.getPawnMoves(nextPawn,
					blackPieces | whitePieces, whitePieces);
			// printBitboard(bitboardOfMoves);

			possibleStates.addAll(generateNextMoves(nextPawn, bitboardOfMoves,
					copyCurrentBoard(currentBoard)));

			String blackPawnsString = Long.toBinaryString(blackPawns);
			// stops String.substring(1) throwing an error
			if (blackPawnsString.length() == 1) {
				break;
			}
			blackPawns = Long.parseLong(blackPawnsString.substring(1), 2);
		}

		while (blackKnights != 0) {

			long nextKnight = Long.highestOneBit(blackKnights);
			// System.out.println("The next piece is: ");
			// printBitboard(nextPawn);
			// System.out.println("With possible moves: ");
			long bitboardOfMoves = BlackPieces.getKnightMoves(nextKnight,
					blackPieces);
			// printBitboard(bitboardOfMoves);

			possibleStates.addAll(generateNextMoves(nextKnight,
					bitboardOfMoves, copyCurrentBoard(currentBoard)));

			String blackKnightsString = Long.toBinaryString(blackKnights);
			// stops String.substring(1) throwing an error
			if (blackKnightsString.length() == 1) {
				break;
			}
			blackKnights = Long.parseLong(blackKnightsString.substring(1), 2);
		}

		while (blackBishops != 0) {
			long nextBishop = Long.highestOneBit(blackBishops);
			// System.out.println("The next piece is: ");
			// printBitboard(nextPawn);
			// System.out.println("With possible moves: ");
			long bitboardOfMoves = BlackPieces.getBishopMoves(nextBishop,
					blackPieces | whitePieces, blackPieces);
			// printBitboard(bitboardOfMoves);

			possibleStates.addAll(generateNextMoves(nextBishop,
					bitboardOfMoves, copyCurrentBoard(currentBoard)));

			String blackBishopsString = Long.toBinaryString(blackBishops);
			// stops String.substring(1) throwing an error
			if (blackBishopsString.length() == 1) {
				break;
			}
			blackBishops = Long.parseLong(blackBishopsString.substring(1), 2);
		}

		while (blackQueens != 0) {
			long nextQueen = Long.highestOneBit(blackQueens);
			// System.out.println("The next piece is: ");
			// printBitboard(nextPawn);
			// System.out.println("With possible moves: ");
			long bitboardOfMoves = BlackPieces.getQueenMoves(blackQueens,
					blackPieces | whitePieces, blackPieces);
			// printBitboard(bitboardOfMoves);

			possibleStates.addAll(generateNextMoves(nextQueen, bitboardOfMoves,
					copyCurrentBoard(currentBoard)));

			String blackQueensString = Long.toBinaryString(blackQueens);
			// stops String.substring(1) throwing an error
			if (blackQueensString.length() == 1) {
				break;
			}
			blackQueens = Long.parseLong(blackQueensString.substring(1), 2);
		}

		while (blackRooks != 0) {
			long nextRook = Long.highestOneBit(blackRooks);
			// System.out.println("The next piece is: ");
			// printBitboard(nextPawn);
			// System.out.println("With possible moves: ");
			long bitboardOfMoves = BlackPieces.getRookMoves(nextRook,
					blackPieces | whitePieces, blackPieces);
			// printBitboard(bitboardOfMoves);

			possibleStates.addAll(generateNextMoves(nextRook, bitboardOfMoves,
					copyCurrentBoard(currentBoard)));

			String blackRooksString = Long.toBinaryString(blackRooks);
			// stops String.substring(1) throwing an error
			if (blackRooksString.length() == 1) {
				break;
			}
			blackRooks = Long.parseLong(blackRooksString.substring(1), 2);
		}

		long kingMovesBitboard = BlackPieces.getKingMoves(blackKing,
				blackPieces, whiteAttackingSquares);
		possibleStates.addAll(generateNextMoves(blackKing, kingMovesBitboard,
				currentBoard));

		if (possibleStates.isEmpty()) {
			System.out.println("Checkmate!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
		return possibleStates;

	}

	/**
	 * Calculates the possible legal moves from a certain piece given a current
	 * board state, the bitboard of the piece to be moved and the bitboard of
	 * potential moves for that piece.
	 * 
	 * @param nextPieceBitboard
	 *            - The bitboard of the next piece to be moved.
	 * @param bitboardOfMoves
	 *            - The bitboard of every possible move which may or not be
	 *            valid.
	 * @param currentBoard
	 *            - The current board state
	 * @return - The list of valid moves.
	 */
	private static Vector<String[][]> generateNextMoves(long nextPieceBitboard,
			long bitboardOfMoves, String[][] currentBoard) {
		count++;
		// System.out.println("generating possible states here");
		Vector<String[][]> listOfMoves = new Vector<String[][]>(20, 10);

		// working through each bit
		while (bitboardOfMoves != 0) {
			String[][] tempBoard = copyCurrentBoard(currentBoard);
			long nextMove = Long.highestOneBit(bitboardOfMoves);
			// printBitboard(nextMove);

			// removing the leftmost 1 from the possible moves
			String bitboardOfMovesString = Long.toBinaryString(bitboardOfMoves);
			if (bitboardOfMovesString.length() == 1) {
				break;
			}
			bitboardOfMoves = Long.parseLong(
					bitboardOfMovesString.substring(1), 2);

			int fromSquare = Long.toBinaryString(nextPieceBitboard).length() - 1;
			int toSquare = Long.toBinaryString(nextMove).length() - 1;
			// System.out.println("From square: " + fromSquare);
			// System.out.println("To square: " + toSquare);

			// System.out.println("x: " + (fromSquare % 8) + " y: "
			// + (fromSquare / 8));
			// System.out
			// .println("x: " + (toSquare % 8) + " y: " + (toSquare / 8));
			tempBoard[toSquare % 8][toSquare / 8] = tempBoard[fromSquare % 8][fromSquare / 8];
			tempBoard[fromSquare % 8][fromSquare / 8] = " ";

			boolean moveIsValid = BoardManager.IsSelfCheck(
					tempBoard,
					tempBoard[toSquare % 8][toSquare / 8].toUpperCase().equals(
							tempBoard[toSquare % 8][toSquare / 8]));

			if (moveIsValid) {
				listOfMoves.add(tempBoard);
				// printBoard(tempBoard);
			}
		}

		// printBoard(currentBoard);
		return listOfMoves;
	}

	private static String[][] copyCurrentBoard(String[][] currentBoard) {
		String[][] temp = new String[currentBoard.length][currentBoard.length];

		for (int x = 0; x < temp.length; x++) {
			for (int y = 0; y < temp.length; y++) {
				temp[x][y] = new String(currentBoard[x][y]);
			}
		}
		return temp;
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
	public static void printBoard(String[][] board) {
		for (int y = 7; y >= 0; y--) {
			for (int x = 0; x < board.length; x++) {
				String temp = board[x][y];
				if (temp.equals(" ")) {
					System.out.print(", ");
				} else {
					System.out.print(board[x][y] + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	public static void printCount() {
		System.out.println(count);
	}
}

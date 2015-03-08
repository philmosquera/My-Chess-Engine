package other;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import board.ChessBoard;

public class PerftTesting {

	public static void main(String[] args) {

		// new PerftTesting();

		new ChessBoard();

	}

	public PerftTesting() {

		long count = 1;
		long numberOfMismatches = 0;
		long readDepth = 0;
		Scanner myScanner;
		ChessBoard myboard = new ChessBoard();
		try {
			myScanner = new Scanner(Paths.get("src/other/perftsuite.txt"));
			while (myScanner.hasNextLine()) {

				myScanner.useDelimiter(" ;");
				myboard.newGameFromFEN(myScanner.next());
				readDepth = Long.parseLong(myScanner.next().substring(3));
				readDepth = Long.parseLong(myScanner.next().substring(3));
				readDepth = Long.parseLong(myScanner.next().substring(3));
				readDepth = Long.parseLong(myScanner.next().substring(3));
				readDepth = Long.parseLong(myScanner.next().substring(3));
				long calcDepth = myboard.generateDepthMoves(5);
				if (readDepth != calcDepth) {
					System.out.println();
					System.out.println("mismatch");
					System.out.println("calc depth " + calcDepth);
					System.out.println("read depth " + readDepth);
					System.out.println(count);
					System.out.println("==============");
					myboard.printBoard();
					System.out.println();
					System.out.println();
					numberOfMismatches++;
				}
				System.out.println("calc depth " + calcDepth);
				System.out.println("read depth " + readDepth);
				System.out.println(count);
				myScanner.nextLine();
				count++;
			}
			System.out.println("Missed : " + numberOfMismatches);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

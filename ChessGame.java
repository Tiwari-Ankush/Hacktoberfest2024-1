import java.util.Scanner;

class ChessGame {
    private static final int SIZE = 8;
    private static String[][] board;

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter your move (e.g., e2 e4): ");
            String move = scanner.nextLine();
            if (move.equals("exit")) {
                break;
            }
            String[] positions = move.split(" ");
            if (positions.length == 2) {
                if (makeMove(positions[0], positions[1])) {
                    printBoard();
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            } else {
                System.out.println("Please enter a valid move.");
            }
        }
        scanner.close();
    }

    private static void initializeBoard() {
        board = new String[SIZE][SIZE];
        // Place pieces on the board
        for (int i = 0; i < SIZE; i++) {
            board[1][i] = "P"; // Pawns
            board[6][i] = "p"; // Pawns
        }
        board[0][0] = board[0][7] = "R"; // Rooks
        board[0][1] = board[0][6] = "N"; // Knights
        board[0][2] = board[0][5] = "B"; // Bishops
        board[0][3] = "Q"; // Queen
        board[0][4] = "K"; // King
        board[7][0] = board[7][7] = "r"; // Rooks
        board[7][1] = board[7][6] = "n"; // Knights
        board[7][2] = board[7][5] = "b"; // Bishops
        board[7][3] = "q"; // Queen
        board[7][4] = "k"; // King
    }

    private static void printBoard() {
        for (int i = SIZE - 1; i >= 0; i--) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean makeMove(String from, String to) {
        int fromRow = 8 - Character.getNumericValue(from.charAt(1));
        int fromCol = from.charAt(0) - 'a';
        int toRow = 8 - Character.getNumericValue(to.charAt(1));
        int toCol = to.charAt(0) - 'a';

        // Validate the move
        if (isValidMove(fromRow, fromCol, toRow, toCol)) {
            // Move the piece
            board[toRow][toCol] = board[fromRow][fromCol];
            board[fromRow][fromCol] = null;
            return true;
        }
        return false;
    }

    private static boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Basic validation: check if the move is within the board limits
        if (fromRow < 0 || fromRow >= SIZE || fromCol < 0 || fromCol >= SIZE ||
            toRow < 0 || toRow >= SIZE || toCol < 0 || toCol >= SIZE) {
            return false;
        }
        // Check if the move is to an empty square or a square occupied by an opponent's piece
        if (board[toRow][toCol] != null && board[fromRow][fromCol].toLowerCase().equals(board[toRow][toCol].toLowerCase())) {
            return false;
        }
        return true;
    }
}

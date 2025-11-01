import java.util.ArrayList;
import java.util.List;

public class ComputerPlayer {
    private ChessPiece[][] pieceGrid;
    private String aiColor;

    public ComputerPlayer(ChessPiece[][] pieceGrid, String aiColor) {
        this.pieceGrid = pieceGrid;
        this.aiColor = aiColor;
    }

    public Move chooseMove() {
        List<Move> allLegalMoves = generateAllLegalMoves();
        Move bestMove = null;
        int bestScore = Integer.MIN_VALUE;

        for (Move move : allLegalMoves) {
            ChessPiece srcPiece = pieceGrid[move.srcRow][move.srcCol];
            ChessPiece targetPiece = pieceGrid[move.destRow][move.destCol];

            // Simulate move
            pieceGrid[move.destRow][move.destCol] = srcPiece;
            pieceGrid[move.srcRow][move.srcCol] = null;

            int score = evaluateBoard();

            // Undo move
            pieceGrid[move.srcRow][move.srcCol] = srcPiece;
            pieceGrid[move.destRow][move.destCol] = targetPiece;

            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private List<Move> generateAllLegalMoves() {
        List<Move> moves = new ArrayList<>();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = pieceGrid[row][col];
                if (piece != null && piece.getColor().equals(aiColor)) {
                    for (int destRow = 0; destRow < 8; destRow++) {
                        for (int destCol = 0; destCol < 8; destCol++) {
                            if (isBoardAwareValidMove(piece, row, col, destRow, destCol)) {
                                moves.add(new Move(row, col, destRow, destCol));
                            }
                        }
                    }
                }
            }
        }

        return moves;
    }

    private int evaluateBoard() {
        int score = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = pieceGrid[row][col];
                if (piece != null) {
                    int value = piece.getValue(); // Add this method to each piece class
                    score += piece.getColor().equals(aiColor) ? value : -value;
                }
            }
        }
        return score;

    }

    private boolean isBoardAwareValidMove(ChessPiece piece, int srcRow, int srcCol, int destRow, int destCol) {
        if (piece instanceof Rook) {
            return ((Rook) piece).isValidMove(srcRow, srcCol, destRow, destCol, pieceGrid);
        } else if (piece instanceof Bishop) {
            return ((Bishop) piece).isValidMove(srcRow, srcCol, destRow, destCol, pieceGrid);
        } else if (piece instanceof Queen) {
            return ((Queen) piece).isValidMove(srcRow, srcCol, destRow, destCol, pieceGrid);
        } else if (piece instanceof Knight) {
            return piece.isValidMove(srcRow, srcCol, destRow, destCol);
        } else if (piece instanceof Pawn) {
            return ((Pawn) piece).isValidMove(srcRow, srcCol, destRow, destCol, pieceGrid);
        }
        return false;
    }
}

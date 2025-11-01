import javax.swing.*;
import java.awt.*;

public class Queen extends ChessPiece {
    private ImageIcon icon;

    public Queen(String color) {

        super(color, "Queen");

        String imagePath = color.equals("white") ? "images/white_queen.png" : "images/black_queen.png";
        this.icon = new ImageIcon(imagePath);
    }

    public ImageIcon getIcon() {
        return icon;
    }

    // Simplified directional check
    @Override
    public boolean isValidMove(int srcRow, int srcCol, int destRow, int destCol) {
        int rowDiff = Math.abs(destRow - srcRow);
        int colDiff = Math.abs(destCol - srcCol);
        return (srcRow == destRow || srcCol == destCol || rowDiff == colDiff)
                && !(srcRow == destRow && srcCol == destCol);
    }

    // Full validation with board awareness
    public boolean isValidMove(int srcRow, int srcCol, int destRow, int destCol, ChessPiece[][] board) {
        if (destRow < 0 || destRow >= 8 || destCol < 0 || destCol >= 8)
            return false;

        if (srcRow == destRow && srcCol == destCol)
            return false;

        if (!isValidMove(srcRow, srcCol, destRow, destCol))
            return false;

        if (!isPathClear(board, srcRow, srcCol, destRow, destCol))
            return false;

        ChessPiece destPiece = board[destRow][destCol];
        if (destPiece != null && destPiece.getColor().equals(this.getColor()))
            return false;

        return true;
    }

    // Path clearance for combined Rook/Bishop logic
    public boolean isPathClear(ChessPiece[][] board, int srcRow, int srcCol, int destRow, int destCol) {
        int rowDiff = Math.abs(destRow - srcRow);
        int colDiff = Math.abs(destCol - srcCol);

        if (srcRow == destRow) { // Horizontal
            int step = srcCol < destCol ? 1 : -1;
            for (int col = srcCol + step; col != destCol; col += step)
                if (board[srcRow][col] != null)
                    return false;
        } else if (srcCol == destCol) { // Vertical
            int step = srcRow < destRow ? 1 : -1;
            for (int row = srcRow + step; row != destRow; row += step)
                if (board[row][srcCol] != null)
                    return false;
        } else if (rowDiff == colDiff) { // Diagonal
            int rowStep = destRow > srcRow ? 1 : -1;
            int colStep = destCol > srcCol ? 1 : -1;
            int row = srcRow + rowStep;
            int col = srcCol + colStep;

            while (row != destRow && col != destCol) {
                if (board[row][col] != null)
                    return false;
                row += rowStep;
                col += colStep;
            }
        }

        return true;
    }

    public int getValue() {
        return 3;
    }
}

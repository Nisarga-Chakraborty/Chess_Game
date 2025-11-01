import javax.swing.*;
import java.awt.*;

public class Rook extends ChessPiece {
    private ImageIcon icon;

    // Constructor that sets color and loads corresponding icon
    public Rook(String color) {
        super(color, "rook");

        String imagePath = color.equals("white") ? "images/white_rook.png" : "images/black_rook.png";
        this.icon = new ImageIcon(imagePath);
    }

    public ImageIcon getIcon() {
        return icon;
    }

    // Checks basic rook movement
    @Override
    public boolean isValidMove(int srcRow, int srcCol, int destRow, int destCol) {
        return srcRow == destRow || srcCol == destCol;
    }

    // Validates move considering board bounds, path clearance, and destination
    // logic
    @Override
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
        return destPiece == null || !destPiece.getColor().equals(this.getColor());
    }

    // Clears the rook's path (horizontal or vertical only)
    private boolean isPathClear(ChessPiece[][] board, int srcRow, int srcCol, int destRow, int destCol) {
        if (srcRow == destRow) {
            int step = srcCol < destCol ? 1 : -1;
            for (int col = srcCol + step; col != destCol; col += step) {
                if (board[srcRow][col] != null)
                    return false;
            }
        } else if (srcCol == destCol) {
            int step = srcRow < destRow ? 1 : -1;
            for (int row = srcRow + step; row != destRow; row += step) {
                if (board[row][srcCol] != null)
                    return false;
            }
        }
        return true;
    }

    public int getValue() {
        return 3;
    }
}

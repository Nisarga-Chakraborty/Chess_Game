import javax.swing.*;
import java.awt.*;

public class Bishop extends ChessPiece {
    private ImageIcon icon;

    public Bishop(String color) {
        super(color, "Bishop");

        String imagePath = color.equals("white") ? "images/white_bishop.png" : "images/black_bishop.png";
        this.icon = new ImageIcon(imagePath);
    }

    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public boolean isValidMove(int srcRow, int srcCol, int destRow, int destCol) {
        int rowDiff = Math.abs(destRow - srcRow);
        int colDiff = Math.abs(destCol - srcCol);
        // Diagonal movement condition
        return rowDiff == colDiff && !(srcRow == destRow && srcCol == destCol);
    }

    public boolean isValidMove(int srcRow, int srcCol, int destRow, int destCol, ChessPiece[][] board) {
        // Check if destination is inside board boundaries
        if (destRow < 0 || destRow >= 8 || destCol < 0 || destCol >= 8)
            return false;

        // Check if move is not staying in the same cell
        if (srcRow == destRow && srcCol == destCol)
            return false;

        // Check if movement is straight (rook movement)
        if (!isValidMove(srcRow, srcCol, destRow, destCol))
            return false;

        // Check if path is clear
        if (!isPathClear(board, srcRow, srcCol, destRow, destCol))
            return false;

        // Check destination cell: cannot capture own piece
        ChessPiece destPiece = board[destRow][destCol];
        if (destPiece != null && destPiece.getColor().equals(this.getColor()))
            return false;

        return true;
    }

    public boolean isPathClear(ChessPiece[][] board, int srcRow, int srcCol, int destRow, int destCol) {
        int rowStep = (destRow > srcRow) ? 1 : -1;
        int colStep = (destCol > srcCol) ? 1 : -1;

        int row = srcRow + rowStep;
        int col = srcCol + colStep;

        while (row != destRow && col != destCol) {
            if (board[row][col] != null)
                return false;
            row += rowStep;
            col += colStep;
        }

        return true;
    }

    public int getValue() {
        return 3; // Replace with appropriate value per piece
    }
}

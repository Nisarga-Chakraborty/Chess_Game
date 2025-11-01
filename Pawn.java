import javax.swing.*;
import java.awt.*;

public class Pawn extends ChessPiece {
    private ImageIcon icon;
    // Constructor that sets color and loads corresponding icon

    public Pawn(String color) {
        super(color, "Pawn");

        String imagePath = color.equals("white") ? "images/white_pawn.png" : "images/black_pawn.png";
        this.icon = new ImageIcon(imagePath);

    }

    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public boolean isValidMove(int srcRow, int srcCol, int destRow, int destCol) {
        int direction = this.getColor().equals("white") ? -1 : 1;

        // Forward move by 1
        if (destCol == srcCol && destRow == srcRow + direction)
            return true;

        // Initial 2-step move
        if (destCol == srcCol && srcRow == (this.getColor().equals("white") ? 6 : 1)
                && destRow == srcRow + 2 * direction)
            return true;

        // Diagonal capture
        if (Math.abs(destCol - srcCol) == 1 && destRow == srcRow + direction)
            return true;

        return false;
    }

    // Board-aware version
    public boolean isValidMove(int srcRow, int srcCol, int destRow, int destCol, ChessPiece[][] board) {
        int direction = this.getColor().equals("white") ? -1 : 1;

        // Out-of-board check
        if (destRow < 0 || destRow >= 8 || destCol < 0 || destCol >= 8)
            return false;

        // Moving forward to empty space
        if (destCol == srcCol && destRow == srcRow + direction && board[destRow][destCol] == null)
            return true;

        // Initial 2-step forward (both squares must be clear)
        if (destCol == srcCol && srcRow == (this.getColor().equals("white") ? 6 : 1)
                && destRow == srcRow + 2 * direction
                && board[srcRow + direction][srcCol] == null
                && board[destRow][destCol] == null)
            return true;

        // Diagonal capture
        if (Math.abs(destCol - srcCol) == 1 && destRow == srcRow + direction) {
            ChessPiece destPiece = board[destRow][destCol];
            if (destPiece != null && !destPiece.getColor().equals(this.getColor()))
                return true;
        }

        return false;
    }

    public int getValue() {
        return 3;
    }
}

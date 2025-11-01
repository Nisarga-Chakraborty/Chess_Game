import javax.swing.*;
import java.awt.*;

// King.java
public class King extends ChessPiece {
    private ImageIcon icon;

    public King(String color) {
        super(color, "King");

        String imagePath = color.equals("white") ? "images/white_king.png" : "images/black_king.png";
        this.icon = new ImageIcon(imagePath);
    }

    public ImageIcon getIcon() {
        return icon;
    }

    // Simple directional check (one square only)
    @Override
    public boolean isValidMove(int srcRow, int srcCol, int destRow, int destCol) {
        int rowDiff = Math.abs(destRow - srcRow);
        int colDiff = Math.abs(destCol - srcCol);

        return (rowDiff <= 1 && colDiff <= 1) && !(rowDiff == 0 && colDiff == 0);
    }

    // Board-aware validation
    public boolean isValidMove(int srcRow, int srcCol, int destRow, int destCol, ChessPiece[][] board) {
        if (destRow < 0 || destRow >= 8 || destCol < 0 || destCol >= 8)
            return false;

        if (!isValidMove(srcRow, srcCol, destRow, destCol))
            return false;

        ChessPiece destPiece = board[destRow][destCol];
        if (destPiece != null && destPiece.getColor().equals(this.getColor()))
            return false;

        // You might later add: isInCheck(destRow, destCol) to prevent illegal move into
        // check

        return true;
    }

    public int getValue() {
        return 3;
    }
}

import javax.swing.*;
import java.awt.*;

public class Knight extends ChessPiece {
    private ImageIcon icon;

    public Knight(String color) {
        super(color, "knight");

        String path = color.equals("white") ? "images/white_knight.png" : "images/black_knight.png";
        Image img = new ImageIcon(path).getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    // Basic move without board state
    @Override
    public boolean isValidMove(int srcRow, int srcCol, int destRow, int destCol) {
        int rowDiff = Math.abs(destRow - srcRow);
        int colDiff = Math.abs(destCol - srcCol);
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    // Board-aware move — just calls the basic one
    @Override
    public boolean isValidMove(int srcRow, int srcCol, int destRow, int destCol, ChessPiece[][] board) {
        // Check board boundaries
        if (destRow < 0 || destRow >= 8 || destCol < 0 || destCol >= 8)
            return false;

        if (!isValidMove(srcRow, srcCol, destRow, destCol))
            return false;

        // Prevent capturing own color
        ChessPiece destPiece = board[destRow][destCol];
        return destPiece == null || !destPiece.getColor().equals(this.getColor());
    }

    public int getValue() {
        return 3;
    }
}

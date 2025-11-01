import javax.swing.Icon;
import javax.swing.ImageIcon;

public abstract class ChessPiece {
    protected String color;
    protected String name;

    public ChessPiece(String color, String name) {
        this.color = color;
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    // Each piece must implement this
    public abstract boolean isValidMove(int srcRow, int srcCol, int destRow, int destCol);

    public abstract boolean isValidMove(int srcRow, int srcCol, int destRow, int destCol, ChessPiece[][] board);

    public ImageIcon getIcon() {
        return null;
    }

    public abstract int getValue();

}

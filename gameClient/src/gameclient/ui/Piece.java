package gameclient.ui;

public class Piece {

    public Piece(int x, int y, PieceColor color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public PieceColor getColor() {
        return color;
    }

    public boolean inTheSamePlaceWith(Piece other) {
        return this.x == other.x && this.y == other.y;
    }

    private final int x;
    private final int y;
    private final PieceColor color;
}

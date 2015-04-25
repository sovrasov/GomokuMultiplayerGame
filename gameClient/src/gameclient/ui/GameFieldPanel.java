package gameclient.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GameFieldPanel extends JPanel {

    public GameFieldPanel(GameClientController controller) {
        this.controller = controller;

        xSteps = 15;
        ySteps = 15;
        computeSizeCharacteristics();

        addMouseListener(new PiecePlacementListener());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        computeSizeCharacteristics();
        drawGrid(g);
        drawPieces(g);
    }


    private class PiecePlacementListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent me) {
            if (me.getButton() != MouseEvent.BUTTON1)
                return;

            int xClick = me.getX();
            int yClick = me.getY();

            int xPiece = (int)Math.floor(xClick / xStep);
            int yPiece = (int)Math.floor(yClick / yStep);

            controller.tryToPutPlaceholderPiece(xPiece, yPiece);
        }

        @Override
        public void mousePressed(MouseEvent me) {
            // ignore
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            // ignore
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            // ignore
        }

        @Override
        public void mouseExited(MouseEvent me) {
            // ignore
        }

    }

    private void computeSizeCharacteristics() {
        Rectangle bounds = new Rectangle(0, 0, getWidth(), getHeight());

        left = bounds.x;
        right = bounds.x + bounds.width;
        bottom = bounds.y + bounds.height;
        top = bounds.y;

        xStep = left + (float)(right - left) / xSteps;
        yStep = top + (float)(bottom - top) / ySteps;

        xStart = (int)Math.floor(left + 0.5 * xStep);
        xStop = (int)Math.floor(right - 0.5 * xStep);
        yStart = (int)Math.floor(top + 0.5 * yStep);
        yStop = (int)Math.floor(bottom - 0.5 * yStep);
    }

    private void drawGrid(Graphics g) {
        Rectangle bounds = g.getClipBounds();
        g.setColor(new Color(0xE6, 0xC5, 0x7E));
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

        g.setColor(Color.BLACK);
        for (int i = 0; i < xSteps; ++i) {
            int xCurrent = (int)(xStart + i * xStep);
            g.drawLine(xCurrent, yStart, xCurrent, yStop);
        }
        for (int i = 0; i < ySteps; ++i) {
            int yCurrent = (int)(yStart + i * yStep);
            g.drawLine(xStart, yCurrent, xStop, yCurrent);
        }
    }

    private void drawPieces(Graphics g) {
        ArrayList<Piece> allPieces = new ArrayList<>();
        GameClientModel model = controller.getModel();

        allPieces.addAll(model.getMyPieces());
        allPieces.addAll(model.getOpponentsPieces());
        Piece placeholderPiece = model.getPlaceholderPiece();

        if (placeholderPiece != null) {
            allPieces.add(placeholderPiece);
        }

        for (Piece piece : allPieces) {
            switch (piece.getColor()) {
            case WHITE:
                g.setColor(Color.WHITE);
                break;
            case BLACK:
                g.setColor(Color.BLACK);
                break;
            case RED:
                g.setColor(Color.RED);
                break;
            }
            g.fillOval(
                (int)(left + xStep * (piece.getX() + 0.2f)),
                (int)(top + yStep * (piece.getY() + 0.2f)),
                (int)Math.floor(xStep * 0.6f), (int)Math.floor(yStep * 0.6f));
        }
    }

    private final GameClientController controller;

    private final int ySteps;
    private final int xSteps;

    private int left;
    private int right;
    private int top;
    private int bottom;
    private float xStep;
    private float yStep;
    private int xStart;
    private int xStop;
    private int yStart;
    private int yStop;
}

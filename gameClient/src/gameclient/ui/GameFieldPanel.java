package gameclient.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JPanel;

public class GameFieldPanel extends JPanel {

    public GameFieldPanel(GameClientController controller) {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGrid(g);
    }


    private void drawGrid(Graphics g) {
        Rectangle bounds = g.getClipBounds();
        g.setColor(new Color(0xE6, 0xC5, 0x7E));
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

        int left = bounds.x;
        int right = bounds.x + bounds.width;
        int bottom = bounds.y + bounds.height;
        int top = bounds.y;

        final int xSteps = 15;
        final int ySteps = 15;

        float xStep = left + (float)(right - left) / xSteps;
        float yStep = top + (float)(bottom - top) / ySteps;

        int xStart = (int)Math.floor(left + 0.5 * xStep);
        int xStop = (int)Math.floor(right - 0.5 * xStep);
        int yStart = (int)Math.floor(top + 0.5 * yStep);
        int yStop = (int)Math.floor(bottom - 0.5 * yStep);

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
}

package gui.customcomponents;

import javax.swing.*;
import java.awt.*;

/**
 * @author Thorsten Zieres, 1297197
 */
public class JCustomPaperPanel extends JPanel {

    private final int SIZE = 10;
    private final int ARC = 14;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = GraphicsUtil.generateGraphics2D(g);
        GraphicsUtil.clearBackground(this, g2);
        g2.setColor(Color.BLACK);
        GraphicsUtil.drawShadow(g2, SIZE, 0.03f, offset ->
                g2.fillRoundRect(offset + SIZE, offset + SIZE, getWidth() - SIZE * 2, getHeight() - SIZE * 2, ARC, ARC)
        );
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(SIZE, SIZE, getWidth() - SIZE * 2, getHeight() - SIZE * 2, ARC, ARC);
    }

}

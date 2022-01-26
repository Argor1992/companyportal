package gui.customcomponents;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * @author Thorsten Zieres, 1297197
 */
public class GraphicsUtil {

    private GraphicsUtil() {}

    public static Graphics2D generateGraphics2D(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return g2;
    }

    public static void clearBackground(JComponent component, Graphics2D g2) {
        Container currentContainer = component;
        while(currentContainer.getParent() != null && !currentContainer.getParent().isOpaque())
            currentContainer = currentContainer.getParent();

        clearBackground(component, g2, currentContainer.getParent().getBackground());
    }

    public static void clearBackground(JComponent component, Graphics2D g2, Color background) {
        g2.setColor(background);
        g2.fillRect(0, 0, component.getWidth(), component.getHeight());
    }

    public static void drawShadow(Graphics2D g2, int size, float opacity, Consumer<Integer> drawable) {
        Composite compositeCache = g2.getComposite();

        for(int i = 0; i < (double) size; i++) {
            float alpha = (25.0f * ((i+1) / (float) size) * opacity);
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha / 100.0f);
            g2.setComposite(ac);
            drawable.accept(size - i);
        }
        g2.setComposite(compositeCache);
    }
}

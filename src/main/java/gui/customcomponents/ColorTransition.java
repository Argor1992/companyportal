package gui.customcomponents;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Thorsten Zieres, 1297197
 */
public class ColorTransition {

    private final int FRAME_TIME_MILLIS = 10;
    private final JComponent component;
    private double progress = 0;

    private Color currentColor = new Color(0, 0, 0, 0);
    private Color futureColor = new Color(0, 0, 0, 0);

    private boolean inTransition = false;

    public ColorTransition(JComponent component, Color currentColor) {
        this.component = component;
        setColorImmediately(currentColor);
    }

    public Color getCurrentColor() {
        return new Color(
                calculateColor(currentColor.getRed(),futureColor.getRed()),
                calculateColor(currentColor.getGreen(),futureColor.getGreen()),
                calculateColor(currentColor.getBlue(),futureColor.getBlue()),
                calculateColor(currentColor.getAlpha(),futureColor.getAlpha())
        );
    }

    public void setColorToTransitionTo(Color c, long durationMillis) {
        setColorImmediately(getCurrentColor());
        this.futureColor = c;
        if (!inTransition)
            step(durationMillis);
    }

    public void setColorImmediately(Color c) {
        this.currentColor = c;
        this.progress = 0;
    }

    private void step(long durationMillis) {
        inTransition = true;
        progress = Math.min(1, progress + ((double)FRAME_TIME_MILLIS / durationMillis));
        component.repaint();
        if (progress < 1)
            MainExecutorService.getInstance().schedule(() -> step(durationMillis), FRAME_TIME_MILLIS, TimeUnit.MILLISECONDS);
        else
            inTransition = false;
    }

    private int calculateColor(int current, int future) {
        return (int) (current + (future - current) * progress);
    }
}

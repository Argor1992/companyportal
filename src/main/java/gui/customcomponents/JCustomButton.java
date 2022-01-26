package gui.customcomponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Thorsten Zieres, 1297197
 */
public class JCustomButton extends JButton {

    private final ColorTransition colorTransition = new ColorTransition(this, new Color(0, 0, 0, 0));
    private final ButtonStyle buttonStyle;

    public JCustomButton() {
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setFont(getFont().deriveFont(Font.BOLD, 14));

        this.buttonStyle = new ButtonStyle(
                ColorPalette.PRIMARY,
                255,
                60,
                2,
                Color.WHITE
        );
        setForeground(buttonStyle.textColor);
        addTransitionEffects();
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(super.getMinimumSize().width, 36);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = GraphicsUtil.generateGraphics2D(g);
        GraphicsUtil.clearBackground(this, g2);
        paintBackground(g2);
        super.paintComponent(g);
    }

    private void addTransitionEffects() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                colorTransition.setColorToTransitionTo(
                        new Color(
                                buttonStyle.rolloverValue,
                                buttonStyle.rolloverValue,
                                buttonStyle.rolloverValue,
                                buttonStyle.rolloverStrength
                        ), 100L
                );
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                colorTransition.setColorToTransitionTo(
                        new Color(
                                buttonStyle.rolloverValue,
                                buttonStyle.rolloverValue,
                                buttonStyle.rolloverValue,
                                0
                        ), 100L
                );
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                colorTransition.setColorImmediately(
                        new Color(
                                buttonStyle.rolloverValue,
                                buttonStyle.rolloverValue,
                                buttonStyle.rolloverValue,
                                (int)(buttonStyle.rolloverStrength * buttonStyle.clickMulti)
                        )
                );
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                colorTransition.setColorToTransitionTo(
                        new Color(
                                buttonStyle.rolloverValue,
                                buttonStyle.rolloverValue,
                                buttonStyle.rolloverValue,
                                buttonStyle.rolloverStrength
                        ), 200L
                );
            }
        });

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!getModel().isPressed()) {
                    colorTransition.setColorToTransitionTo(
                            new Color(
                                    buttonStyle.rolloverValue,
                                    buttonStyle.rolloverValue,
                                    buttonStyle.rolloverValue,
                                    buttonStyle.rolloverStrength
                            ), 100L
                    );
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                colorTransition.setColorToTransitionTo(
                        new Color(
                                buttonStyle.rolloverValue,
                                buttonStyle.rolloverValue,
                                buttonStyle.rolloverValue,
                                0
                        ), 100L
                );
            }
        });

        addActionListener(l -> {
            colorTransition.setColorImmediately(
                    new Color(
                            buttonStyle.rolloverValue,
                            buttonStyle.rolloverValue,
                            buttonStyle.rolloverValue,
                            (int)(buttonStyle.rolloverStrength * buttonStyle.clickMulti)
                    )
            );
            colorTransition.setColorToTransitionTo(
                    new Color(
                            buttonStyle.rolloverValue,
                            buttonStyle.rolloverValue,
                            buttonStyle.rolloverValue,
                            buttonStyle.rolloverStrength
                    ), 200L
            );
        });
    }

    private void paintBackground(Graphics2D g2) {
        g2.setColor(buttonStyle.color);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
        Color transitionColor = colorTransition.getCurrentColor();
        if (transitionColor.getAlpha() > 0) {
            g2.setColor(transitionColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
        }
    }

    private static class ButtonStyle {

        private final Color color;
        private final int rolloverValue;
        private final int rolloverStrength;
        private final double clickMulti;
        private final Color textColor;

        public ButtonStyle(Color color, int rolloverValue, int rolloverStrength, double clickMulti, Color textColor) {
            this.color = color;
            this.rolloverValue = rolloverValue;
            this.rolloverStrength = rolloverStrength;
            this.clickMulti = clickMulti;
            this.textColor = textColor;
        }

    }
}

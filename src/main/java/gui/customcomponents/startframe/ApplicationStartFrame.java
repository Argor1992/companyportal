package gui.customcomponents.startframe;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import gui.customcomponents.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author Thorsten Zieres, 1297197
 */
public class ApplicationStartFrame extends JFrame {
    private static final String LOGO_PATH = "./pictures/company_logo.png";
    private static final String SMALL_LOGO_PATH = "./pictures/company_logo_v2.png";

    private JPanel pnMain;
    private JPanel pnHeader;
    private JLabel lbIntro;
    private JPanel pnBody;
    private JPanel pnCenter;
    private JLabel lblLogo;
    private JSeparator sep;
    private JLabel textHeader;
    private JTextArea introductionText;
    private JCustomButton btMain;

    public ApplicationStartFrame() {
        $$$setupUI$$$();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setContentPane(pnMain);
        setIconImage(new ImageIcon(SMALL_LOGO_PATH).getImage());
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Rich Solutions - Demo");
        pnMain.setBackground(ColorPalette.BASE);

        initComponents();
    }

    private void initComponents() {
        pnMain.getRootPane().setDefaultButton(btMain);
        pnHeader.setBackground(ColorPalette.TEXT_BLACK);
        lbIntro.setText("Willkommen zu unserer Demo");
        pnCenter.setVisible(true);
        sep.setForeground(ColorPalette.BASE);
        textHeader.setText("  Willkommen zu unserer IT-Sicherheitsdemo");
        introductionText.setText("Über den Button öffnen sie die interne Website der Rich Solutions. Wenn sie dieses Fenster schließen beendet sich auch die Demo!");
        introductionText.setLineWrap(true);
        introductionText.setWrapStyleWord(true);
        introductionText.setEditable(false);
        btMain.setText("Zur Demo");
        btMain.addActionListener(l -> redirectToWebsite());
    }

    private void redirectToWebsite() {
        try {
            Desktop.getDesktop().browse(new URL("http://localhost:8080/").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createUIComponents() {
        lblLogo = new JLabel(new ImageIcon(LOGO_PATH));
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * >> had to do this otherwise I could not compile with maven
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        pnMain = new JPanel();
        pnMain.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnMain.setBackground(new Color(-592138));
        pnHeader = new JPanel();
        pnHeader.setLayout(new GridLayoutManager(1, 2, new Insets(0, 16, 0, 16), -1, -1));
        pnHeader.setBackground(new Color(-16777216));
        pnHeader.setOpaque(true);
        pnMain.add(pnHeader, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 48), null, null, 0, false));
        lbIntro = new JLabel();
        lbIntro.setForeground(new Color(-1));
        lbIntro.setOpaque(false);
        lbIntro.setText("Intro");
        pnHeader.add(lbIntro, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        pnHeader.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        pnBody = new JPanel();
        pnBody.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnBody.setBackground(new Color(-2302756));
        pnBody.setEnabled(true);
        pnBody.setOpaque(false);
        pnMain.add(pnBody, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JCustomPaperPanel jCustomPaperPanel1 = new JCustomPaperPanel();
        jCustomPaperPanel1.setLayout(new GridLayoutManager(3, 1, new Insets(35, 35, 35, 35), -1, -1));
        jCustomPaperPanel1.setBackground(new Color(-1));
        pnBody.add(jCustomPaperPanel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(400, -1), null, null, 0, false));
        btMain = new JCustomButton();
        btMain.setBackground(new Color(-11558928));
        btMain.setDoubleBuffered(false);
        btMain.setText("Button");
        btMain.putClientProperty("html.disable", Boolean.FALSE);
        jCustomPaperPanel1.add(btMain, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(500, -1), new Dimension(500, -1), new Dimension(500, -1), 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setOpaque(false);
        jCustomPaperPanel1.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 16), null, null, 0, false));
        introductionText = new JTextArea();
        introductionText.setBackground(new Color(-1));
        panel1.add(introductionText, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(500, -1), new Dimension(500, 50), new Dimension(500, -1), 0, false));
        pnCenter = new JPanel();
        pnCenter.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnCenter.setOpaque(false);
        jCustomPaperPanel1.add(pnCenter, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setOpaque(false);
        pnCenter.add(panel2, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 24), null, null, 0, false));
        lblLogo.setText("");
        pnCenter.add(lblLogo, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(500, 210), new Dimension(500, 210), new Dimension(500, 210), 1, false));
        sep = new JSeparator();
        sep.setForeground(new Color(-16777216));
        pnCenter.add(sep, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(500, -1), new Dimension(500, -1), new Dimension(500, -1), 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setOpaque(false);
        pnCenter.add(panel3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(500, 24), new Dimension(500, -1), new Dimension(500, -1), 0, false));
        textHeader = new JLabel();
        textHeader.setText("Introduction");
        pnCenter.add(textHeader, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(500, -1), new Dimension(500, -1), new Dimension(500, -1), 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setOpaque(false);
        pnCenter.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(500, 6), new Dimension(500, -1), new Dimension(500, -1), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnMain;
    }
}

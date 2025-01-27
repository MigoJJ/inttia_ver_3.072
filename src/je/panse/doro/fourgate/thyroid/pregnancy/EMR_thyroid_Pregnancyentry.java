package je.panse.doro.fourgate.thyroid.pregnancy;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import je.panse.doro.GDSEMR_frame;

public class EMR_thyroid_Pregnancyentry {
    private static final String[] BUTTONS = {
        "New Patient for Pregnancy with Thyroid disease",
        "F/U Pregnancy with Hyperthyroidism",
        "F/U Pregnancy with Hypothyroidism",
        "F/U Pregnancy with Non thyroidal illness",
        "F/U Pregnancy with TSH elevation",
        "F/U Pregnancy with TSH low",
        "Quit"
    };
    
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 400;
    private static final int AUTO_CLOSE_DELAY = 300000; // 5 minutes
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Thyroid Pregnancy Management");
        setupFrame(frame);
        addButtons(frame);
        positionFrame(frame);
        setupAutoClose(frame);
        frame.setVisible(true);
    }

    private static void setupFrame(JFrame frame) {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(BUTTONS.length, 1));
    }

    private static void addButtons(JFrame frame) {
        for (String buttonText : BUTTONS) {
            frame.add(createStyledButton(buttonText, e -> handleButtonClick(frame, buttonText)));
        }
    }

    private static JButton createStyledButton(String text, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D g2d) {
                    int h = getHeight();
                    GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(210, 180, 140),
                        0, h, new Color(180, 150, 110)
                    );
                    g2d.setPaint(gradient);
                    g2d.fillRect(0, 0, getWidth(), h);
                }
                super.paintComponent(g);
            }
        };
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(listener);
        return button;
    }

    private static void positionFrame(JFrame frame) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(
            screen.width - FRAME_WIDTH,
            screen.height - FRAME_HEIGHT
        );
    }

    private static void setupAutoClose(JFrame frame) {
        new Timer(AUTO_CLOSE_DELAY, e -> frame.dispose()).start();
    }

    private static void handleButtonClick(JFrame frame, String buttonText) {
        if ("Quit".equals(buttonText)) {
            frame.dispose();
            return;
        }

        if (buttonText.startsWith("New Patient")) {
            EMR_Preg_CC.main(null);
            return;
        }

        updateEMRFrameText(buttonText);
    }

    private static void updateEMRFrameText(String condition) {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String baseCondition = condition.replace("F/U ", "");
        
        GDSEMR_frame.setTextAreaText(0, 
            String.format("F/U [   ] weeks    %s%n\t%s", currentDate, baseCondition));
        GDSEMR_frame.setTextAreaText(7, 
            String.format("%n  #  %s  [%s]", condition, currentDate));
        GDSEMR_frame.setTextAreaText(8, 
            String.format("...Plan F/U [   ] weeks%n\t %s", baseCondition));
    }
}

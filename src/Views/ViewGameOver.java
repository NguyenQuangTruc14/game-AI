package Views;

import javax.swing.*;

public class ViewGameOver extends JFrame {
    public ViewGameOver(String message) {
        super("Game Over");

        JPanel boxMain = new JPanel();
        boxMain.setLayout(new BoxLayout(boxMain, BoxLayout.Y_AXIS));
        boxMain.add(new JLabel("Game Over: " + message + " là người thắng"));
        add(boxMain);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

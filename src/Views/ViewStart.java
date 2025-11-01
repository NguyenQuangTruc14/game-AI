package Views;

import javax.swing.*;

public class ViewStart extends JFrame {
    public ViewStart() {
        super("Start");


        JPanel boxMain = new JPanel();
        boxMain.setLayout(new BoxLayout(boxMain, BoxLayout.Y_AXIS));

        JPanel boxTitle = new JPanel();
        JLabel title = new JLabel("Start Game");
        title.setFont(new java.awt.Font("Tahoma", 1, 24));
        boxTitle.add(title);
        boxMain.add(boxTitle);

        JPanel boxBt = new JPanel();
        JButton btnStart = new JButton("Start");
        boxBt.add(btnStart);
        boxMain.add(boxBt);



        add(boxMain);
        setSize(400, 120);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

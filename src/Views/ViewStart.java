package Views;

import Models.Game;

import javax.swing.*;
import java.awt.*;

public class ViewStart extends JFrame {
    public ViewStart(Game game) {
        super("last turn");


        JPanel boxMain = new JPanel();
        boxMain.setLayout(new BoxLayout(boxMain, BoxLayout.Y_AXIS));

        // title
        JPanel boxTitle = new JPanel();
        JLabel title = new JLabel("LAST TURN");
        title.setFont(new Font("Tahoma", 1, 30));
        title.setForeground(Color.RED);
        boxTitle.add(title);
        boxMain.add(boxTitle);

        // hướng dẫn game play
        JPanel boxHuongDan = new JPanel();
        JLabel huongDanTitle = new JLabel("Hướng dẫn: ");
        huongDanTitle.setFont(new Font("Tahoma", 1, 24));
        huongDanTitle.setForeground(Color.BLUE);
        boxHuongDan.add(huongDanTitle);

        // text hướng dẫn dòng một
        JLabel huongDan1 = new JLabel();
        huongDan1.setText("Khi bắt đầu, người và máy được random ngẫu nhiên 5 chiêu thức trong bộ n chiêu thức");
        huongDan1.setFont(new Font("Tahoma", 1, 18));
        boxHuongDan.add(huongDan1);

        // text hướng dẫn dòng 2
        JLabel huongDan2 = new JLabel();
        huongDan2.setText("Mỗi lượt chơi, người chơi chỉ được chọn 1 trong 5 chiêu để sử dụng.");
        huongDan2.setFont(new Font("Tahoma", 1, 18));
        boxHuongDan.add(huongDan2);

        //text hướng dẫn dòng 3
        JLabel huongDan3 = new JLabel();
        huongDan3.setText("Có 3 loại chiêu thức: tấn công, tăng giáp cho bản thân, hồi máu cho bản thân");
        huongDan3.setFont(new Font("Tahoma", 1, 18));
        boxHuongDan.add(huongDan3);

        // text hướng dẫn dòng 4
        JLabel huongDan4 = new JLabel();
        huongDan4.setText("Cả hai bắt đầu với 100 máu tối đa và 0 giáp, lương tối đa là 100");
        huongDan4.setFont(new Font("Tahoma", 1, 18));
        boxHuongDan.add(huongDan4);

        // text hướng dẫn dòng 5
        JLabel huongDan5 = new JLabel();
        huongDan5.setText("Khi tấn công, địch sẽ bị trừ giáp trước, khi hết giáp, sát thương sẽ tác động đến máu");
        huongDan5.setFont(new Font("Tahoma", 1, 18));
        boxHuongDan.add(huongDan5);

        // text hướng dẫn dòng 5
        JLabel huongDan6 = new JLabel();
        huongDan6.setText("Mỗi chiêu thức sẽ có một hiệu ứng như: đóng băng, giảm dame, giảm hồi máu");
        huongDan6.setFont(new Font("Tahoma", 1, 18));
        boxHuongDan.add(huongDan6);

        // text hướng dẫn dòng 6
        JLabel huongDan7 = new JLabel();
        huongDan7.setText("Trận đấu sẽ kết thúc khi một trong 2 hết máu.");
        huongDan7.setFont(new Font("Tahoma", 1, 18));
        boxHuongDan.add(huongDan7);

        boxMain.add(boxHuongDan);

        JPanel boxBt = new JPanel();
        JButton btnStart = new JButton("Start game");
        btnStart.setSize(200, 50);
        boxBt.add(btnStart);
        boxMain.add(boxBt);

        btnStart.addActionListener(e -> {
            dispose();
            new ViewGame(game);
        });

        add(boxMain);
        setSize(900, 720);
        // size cố định
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

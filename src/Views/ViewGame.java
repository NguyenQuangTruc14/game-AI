package Views;

import Controllers.DungChieuController;
import Controllers.ExitLuotChoiController;
import Controllers.KiemTraGameOverController;
import Controllers.ThanhTayController;
import Models.ChieuThuc;
import Models.Enum.TrangThaiPlayer;
import Models.Game;
import Models.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ViewGame extends JFrame {
    
    public ViewGame(Game game) {
        super("Game");

        DungChieuController dungChieuController = new DungChieuController();
        KiemTraGameOverController kiemTraGameOverController = new KiemTraGameOverController();
        ExitLuotChoiController exitLuotChoiController = new ExitLuotChoiController();
        ThanhTayController thanhTayController = new ThanhTayController();
        // kiểm tra game over
        if (game.getAi().getMau() == 0) {
            dispose();
            new ViewGameOver("bạn");
            return;
        }
        if (game.getPlayer().getMau() == 0) {
            dispose();
            new ViewGameOver("AI");
            return;
        }
        // nếu người tấn công là 0 thì lược tấn công đó thuộc về AI
        if (game.getNguoiTanCong() == 0) {
            if (game.getAi().getTrangThai().equals(TrangThaiPlayer.CHOANG.name())) {
                if (game.getAi().getListAllChieuThucTa().get(6).getCoolDown() == 0) {
                    Random rd = new Random();
                    int index = rd.nextInt(2);
                    if (index == 0) {
                        Game newGame = thanhTayController.aiThanhTay(game);
                        dispose();
                        new ViewGame(newGame);
                        return;
                    }
                    game.getAi().setChieuAiVuaDung(game.getAi().getListAllChieuThucTa().get(5));
                    Game newGame = exitLuotChoiController.exitLuotChoi(game);
                    dispose();
                    new ViewGame(newGame);
                    return;
                } else {
                    game.getAi().setChieuAiVuaDung(game.getAi().getListAllChieuThucTa().get(5));
                    Game newGame = exitLuotChoiController.exitLuotChoi(game);
                    dispose();
                    new ViewGame(newGame);
                    return;
                }


            }
            Game newGame = dungChieuController.aiDungChieu(game);
            dispose();
            new ViewGame(newGame);
            return;
        }

        Player player = game.getPlayer();
        Player ai = game.getAi();
        
        setLayout(new BorderLayout());
        
        // Panel 5 nút trên (NORTH)
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(new Color(255, 200, 200));
//        JButton btnAiThanhTay = new JButton("thanh tẩy" + " | " + String.valueOf(game.getAi().getListAllChieuThucTa().get(6).getCoolDown()));
//        btnAiThanhTay.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                game.getAi().setTrangThai(TrangThaiPlayer.BINH_THUONG.name());
//                game.setNguoiTanCong(1);
//                dispose();
//                new ViewGame(game);
//                return;
//            }
//        });
//        topPanel.add(btnAiThanhTay);
//        JButton btnAIBoLuot = new JButton("bỏ lượt");
//        btnAIBoLuot.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Game newGame = exitLuotChoiController.exitLuotChoi(game);
//                dispose();
//                new ViewGame(newGame);
//                return;
//            }
//        });
//        topPanel.add(btnAIBoLuot);
        for (int i = 0; i < 7; i++) {
            ChieuThuc chieuThuc = ai.getListAllChieuThucTa().get(i);
            JButton btn = new JButton(chieuThuc.getTen() + " | hôi chiêu: " + chieuThuc.getCoolDown());
            btn.setPreferredSize(new Dimension(200, 50));
            if (game.getAi().getListAllChieuThucTa().get(i).getCoolDown() > 0) {
                btn.setEnabled(false);
            }
            topPanel.add(btn);
        }
        
        // Panel giữa (CENTER)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(240, 240, 240));


        // thông tin địch
        JPanel panelInfoDich = new JPanel();
        panelInfoDich.setLayout(new BoxLayout(panelInfoDich, BoxLayout.Y_AXIS));
        JLabel titleDich = new JLabel("ĐỊCH");
        titleDich.setForeground(Color.RED);
        titleDich.setFont(new Font("Arial", Font.BOLD, 24));
        panelInfoDich.add(titleDich);
        JLabel mauDich = new JLabel("máu: " + String.valueOf(ai.getMau()));
        panelInfoDich.add(mauDich);
        JLabel giapDich = new JLabel("giáp: " + String.valueOf(ai.getGiap()));
        panelInfoDich.add(giapDich);
        JLabel trangThaiDich = new JLabel("trạng thai: " + String.valueOf(ai.getTrangThai()));
        panelInfoDich.add(trangThaiDich);
        for (int i = 0; i < 5; i++) {
            panelInfoDich.add(new JLabel("chiêu " + game.getAi().getListAllChieuThucTa().get(i).getTen() + " | kiểu: " + ai.getListAllChieuThucTa().get(i).getKieuChieuThuc() + " | hiệu ứng: " +
                    ai.getListAllChieuThucTa().get(i).getHieuUngChieuThuc() + " | chỉ số: " + ai.getListAllChieuThucTa().get(i).getChiSo())
            );
        }

        //thông tin của ta
        JPanel panelInfoTa = new JPanel();
        panelInfoTa.setLayout(new BoxLayout(panelInfoTa, BoxLayout.Y_AXIS));
        JLabel titleTa = new JLabel("TA");
        titleTa.setForeground(Color.BLUE);
        titleTa.setFont(new Font("Arial", Font.BOLD, 24));
        panelInfoTa.add(titleTa);
        JLabel mauTa = new JLabel("máu: " + String.valueOf(player.getMau()));
        panelInfoTa.add(mauTa);
        JLabel giapTa = new JLabel("giáp: " + String.valueOf(player.getGiap()));
        panelInfoTa.add(giapTa);
        JLabel trangThaiTa = new JLabel("trạng thai: " + String.valueOf(player.getTrangThai()));
        panelInfoTa.add(trangThaiTa);
        for (int i = 0; i < 6; i++) {
            panelInfoTa.add(new JLabel("chiêu " + game.getPlayer().getListAllChieuThucTa().get(i).getTen() + " | kiểu: " + player.getListAllChieuThucTa().get(i).getKieuChieuThuc() + " | hiệu ứng: " +
                    player.getListAllChieuThucTa().get(i).getHieuUngChieuThuc() + " | chỉ số: " + player.getListAllChieuThucTa().get(i).getChiSo())
            );
        }

        // thông tin trận đấu
        JPanel panelInfoTranDau = new JPanel();
        panelInfoTranDau.setLayout(new BoxLayout(panelInfoTranDau, BoxLayout.Y_AXIS));
        JLabel titleInfoTranDau = new JLabel("thông tin trận đấu");
        titleInfoTranDau.setFont(new Font("Arial", Font.BOLD, 24));
        titleInfoTranDau.setForeground(Color.ORANGE);
        panelInfoTranDau.add(titleInfoTranDau);
        JLabel luotDau = new JLabel("Lượt tấn công của: " + (game.getNguoiTanCong() == 0 ? "địch" : "ta"));
        panelInfoTranDau.add(luotDau);
        JLabel round = new JLabel("Round: " + game.getRound());
        panelInfoTranDau.add(round);
        JLabel skillAI = new JLabel("Ai vừa dùng chieu: " + game.getAi().getChieuAiVuaDung().getTen());
        panelInfoTranDau.add(skillAI);


        centerPanel.add(panelInfoDich);
        centerPanel.add(panelInfoTa);
        centerPanel.add(panelInfoTranDau);
        
        // Panel 5 nút dưới (SOUTH)
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(new Color(200, 255, 200));
//        JButton btnTaThanhTay = new JButton("thanh tẩy" + " | " + String.valueOf(player.getListAllChieuThucTa().get(5).getCoolDown()));
//        btnTaThanhTay.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Game newGame = thanhTayController.taThanhTay(game);
//                dispose();
//                new ViewGame(newGame);
//                return;
//            }
//        });
//        bottomPanel.add(btnTaThanhTay);
        JButton btnTaBoLuot = new JButton("bỏ lượt");
        btnTaBoLuot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game newGame = exitLuotChoiController.exitLuotChoi(game);
                dispose();
                new ViewGame(newGame);
                return;

            }
        });
        bottomPanel.add(btnTaBoLuot);
        for (int i = 0; i < 6; i++) {
            ChieuThuc chieuThuc = player.getListAllChieuThucTa().get(i);
            JButton btn = new JButton(chieuThuc.getTen() + " | hổi chiêu: " + chieuThuc.getCoolDown());
            btn.setPreferredSize(new Dimension(200, 50));
            int index = i;
            if (game.getPlayer().getListAllChieuThucTa().get(index).getCoolDown() > 0) {
                btn.setEnabled(false);
            } else if (game.getPlayer().getTrangThai().equals(TrangThaiPlayer.CHOANG.name())) {
                if (game.getPlayer().getListAllChieuThucTa().get(i).equals(game.getPlayer().getListAllChieuThucTa().get(5))) {
                    btn.setEnabled(true);
                } else {
                    btn.setEnabled(false);
                }
            }
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


//                        if (player.getTrangThai().equals(TrangThaiPlayer.CHOANG.name())) {
//                            game.setNguoiTanCong(0);
//                            dispose();
//                            new ViewGame(game);
//                            return;
//
//                        } else {
                            Game newGame = dungChieuController.taDungChieu(game, index);
                            dispose();
                            new ViewGame(newGame);
                            return;
//                        }


                }
            });
            bottomPanel.add(btn);
        }
        
        // Thêm vào frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
//        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

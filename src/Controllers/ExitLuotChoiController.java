package Controllers;

import Models.Game;
import Models.Player;

public class ExitLuotChoiController {
    public Game exitLuotChoi(Game game) {
        //set round và nguoi tan cong
        game.setRound(game.getRound() + 1);
        if (game.getNguoiTanCong() == 0) {
            game.setNguoiTanCong(1);
        } else {
            game.setNguoiTanCong(0);
        }

        Player ta = game.getPlayer();
        Player ai = game.getAi();

        // set hổi chiêu
        for (int i = 0; i < ta.getListAllChieuThucTa().size(); i++) {
            if (ta.getListAllChieuThucTa().get(i).getCoolDown() > 0) {
                ta.getListAllChieuThucTa().get(i).setCoolDown(ta.getListAllChieuThucTa().get(i).getCoolDown() - 1);
            } else {
                ta.getListAllChieuThucTa().get(i).setCoolDown(0);
            }
        }
        for (int i = 0; i < ai.getListAllChieuThucTa().size(); i++) {
            if (ai.getListAllChieuThucTa().get(i).getCoolDown() > 0) {
                ai.getListAllChieuThucTa().get(i).setCoolDown(ai.getListAllChieuThucTa().get(i).getCoolDown() - 1);
            } else {
                ai.getListAllChieuThucTa().get(i).setCoolDown(0);
            }
        }

        return game;
    }
}

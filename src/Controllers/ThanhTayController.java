package Controllers;

import Models.Enum.HieuUngChieuThuc;
import Models.Enum.KieuChieuThuc;
import Models.Enum.TrangThaiPlayer;
import Models.Game;
import Models.Player;

public class ThanhTayController {
    public Game taThanhTay(Game game) {
        Player ta = game.getPlayer();
        Player ai = game.getAi();
        ta.setTrangThai(TrangThaiPlayer.BINH_THUONG.name());
        ta.getListAllChieuThucTa().get(5).setCoolDown(6);



        Game newGame = new Game(ta, ai);
        newGame.setRound(game.getRound());
        newGame.setNguoiTanCong(game.getNguoiTanCong());
        return newGame;
    }

    public Game aiThanhTay(Game game) {
        Player ai = game.getAi();
        ai.setTrangThai(TrangThaiPlayer.BINH_THUONG.name());
        ai.getListAllChieuThucTa().get(6).setCoolDown(6);
        ai.setChieuAiVuaDung(ai.getListAllChieuThucTa().get(5));
        Player ta = game.getPlayer();


        Game newGame = new Game(ta, ai);
        newGame.setRound(game.getRound());
        newGame.setNguoiTanCong(game.getNguoiTanCong());
        return newGame;
    }
}

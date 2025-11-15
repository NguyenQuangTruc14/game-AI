package Controllers;

import Models.ChieuThuc;
import Models.Enum.HieuUngChieuThuc;
import Models.Enum.KieuChieuThuc;
import Models.Enum.TrangThaiPlayer;
import Models.Game;
import Models.Player;
import Services.SeviceAi;

import java.util.HashMap;
import java.util.Map;

public class DungChieuController {
    ThanhTayController thanhTayController = new ThanhTayController();

//    phương thức ta dùng chiêu

    public Game taDungChieu(Game game, int indexChieuThuc) {


        Player ta = game.getPlayer();
        String trangThaiTa = ta.getTrangThai();
        String hieuUngChieuThuc = ta.getListAllChieuThucTa().get(indexChieuThuc).getHieuUngChieuThuc();
        String kieuChiuThuc = ta.getListAllChieuThucTa().get(indexChieuThuc).getKieuChieuThuc();
        int mauTa = ta.getMau();
        int giapTa = ta.getGiap();

        Player ai = game.getAi();
        String trangThaiAi = ai.getTrangThai();
        int mauAi = ai.getMau();
        int giapAi = ai.getGiap();

        if (indexChieuThuc == ta.getListAllChieuThucTa().size() - 1) {
            return thanhTayController.taThanhTay(game);
        }

        // tăng round lên 1
        game.setRound(game.getRound() + 1);
        // đổi người tấn công
        if (game.getNguoiTanCong() == 0) {
            game.setNguoiTanCong(1);
        } else {
            game.setNguoiTanCong(0);
        }

        // set hồi chiêu cho các chiêu thức của ta và AI
        for (int i = 0; i < ta.getListAllChieuThucTa().size(); i++) {
            if (i == ta.getListAllChieuThucTa().size() - 1) {
                if (ta.getListAllChieuThucTa().get(i).getCoolDown() > 0) {
                    ta.getListAllChieuThucTa().get(i).setCoolDown(ta.getListAllChieuThucTa().get(i).getCoolDown() - 1);
                } else {
                    ta.getListAllChieuThucTa().get(i).setCoolDown(0);
                }
                break;
            }
            if (i != indexChieuThuc) {
                if (ta.getListAllChieuThucTa().get(i).getCoolDown() > 0) {
                    ta.getListAllChieuThucTa().get(i).setCoolDown(ta.getListAllChieuThucTa().get(i).getCoolDown() - 1);
                } else if (ta.getListAllChieuThucTa().get(i).getCoolDown() == 0){
                    ta.getListAllChieuThucTa().get(i).setCoolDown(0);
                }
            } else {
                ta.getListAllChieuThucTa().get(indexChieuThuc).setCoolDown(6);
            }
        }
        for (int i = 0; i < ai.getListAllChieuThucTa().size(); i++) {
            if (i == ai.getListAllChieuThucTa().size() - 1) {
                if (ai.getListAllChieuThucTa().get(i).getCoolDown() > 0) {
                    ai.getListAllChieuThucTa().get(i).setCoolDown(ai.getListAllChieuThucTa().get(i).getCoolDown() - 1);
                } else {
                    ai.getListAllChieuThucTa().get(i).setCoolDown(0);
                }
                break;
            }
            if (i == ai.getListAllChieuThucTa().size() - 2) {
                ai.getListAllChieuThucTa().get(i).setCoolDown(0);
                continue;
            }
            if (ai.getListAllChieuThucTa().get(i).getCoolDown() > 0) {
                ai.getListAllChieuThucTa().get(i).setCoolDown(ai.getListAllChieuThucTa().get(i).getCoolDown() - 1);
            } else if (ai.getListAllChieuThucTa().get(i).getCoolDown() == 0){
                ai.getListAllChieuThucTa().get(i).setCoolDown(0);
            }
        }

        // set hiệu ứng mới tương ứng khi ta dùng chiêu thức
        if (hieuUngChieuThuc.equals(HieuUngChieuThuc.CHOANG.name())) {
            ai.setTrangThai(TrangThaiPlayer.CHOANG.name());
        } else if (hieuUngChieuThuc.equals(HieuUngChieuThuc.GIAM_DAME.name())) {
            ai.setTrangThai(TrangThaiPlayer.GIAM_DAME.name());
        } else {
            ai.setTrangThai(TrangThaiPlayer.GIAM_HOI_MAU.name());
        }

        // set máu, giáp của địch và ta sau khi dùng chiêu thức
        if (kieuChiuThuc.equals(KieuChieuThuc.TAN_CONG.name())) {
            if (!trangThaiTa.equals(TrangThaiPlayer.GIAM_DAME.name())) {
                int dameTa = ta.getListAllChieuThucTa().get(indexChieuThuc).getChiSo();
                if (giapAi > dameTa) {
                    ai.setGiap(giapAi - dameTa);
                } else {
                    ai.setMau(mauAi - (dameTa - ai.getGiap()));
                    ai.setGiap(0);
                }
            } else {
                int dameTa = ta.getListAllChieuThucTa().get(indexChieuThuc).getChiSo() / 2;
                if (giapAi > dameTa) {
                    ai.setGiap(giapAi - dameTa);
                } else {
                    ai.setMau(mauAi - (dameTa - ai.getGiap()));
                    ai.setGiap(0);
                }
            }
        } else if (kieuChiuThuc.equals(KieuChieuThuc.PHONG_THU.name())) {
            if ((100 - giapTa) < ta.getListAllChieuThucTa().get(indexChieuThuc).getChiSo()) {
                ta.setGiap(100);
            } else {
                ta.setGiap(ta.getGiap() + ta.getListAllChieuThucTa().get(indexChieuThuc).getChiSo());
            }
        } else {
            if (ta.getTrangThai().equals(TrangThaiPlayer.GIAM_HOI_MAU.name())){
                int mauHoi = ta.getListAllChieuThucTa().get(indexChieuThuc).getChiSo() / 2;
                if ((100 - mauTa) < mauHoi) {
                    ta.setMau(100);
                } else {
                    ta.setMau(ta.getMau() + mauHoi);
                }
            } else {
                int mauHoi = ta.getListAllChieuThucTa().get(indexChieuThuc).getChiSo();
                if ((100 - mauTa) < mauHoi) {
                    ta.setMau(100);
                } else {
                    ta.setMau(ta.getMau() + mauHoi);
                }
            }
        }


        Game newGame = new Game(ta, ai);
        newGame.setRound(game.getRound());
        newGame.setNguoiTanCong(game.getNguoiTanCong());
        return newGame;

    }

    //phương thức ai dùng chiêu

    public Game aiDungChieu(Game game) {
        SeviceAi seviceAi = new SeviceAi();

        // lấy danh sách chiêu thức đã hết hồi chiêu của AI
        Map<Integer, ChieuThuc> listChieuThucCoTheDung = new HashMap<>();
        for (int i = 0; i < game.getAi().getListAllChieuThucTa().size(); i++) {
            if (game.getAi().getListAllChieuThucTa().get(i).getCoolDown() == 0) {
                listChieuThucCoTheDung.put(i, game.getAi().getListAllChieuThucTa().get(i));
            }
        }

        // chiêu thức ai dùng để tối ưu chiến thắng
        ChieuThuc chieuThucDuocChon = seviceAi.chonChieuThuc(listChieuThucCoTheDung, game);
        game.getAi().setChieuAiVuaDung(chieuThucDuocChon);
        // lấy vi trí index của chiêu được chọn trong danh sách chiêu thức
        int indexChieuThuc = -1;
        for (int i = 0; i < game.getAi().getListAllChieuThucTa().size(); i++) {
            if (game.getAi().getListAllChieuThucTa().get(i).equals(chieuThucDuocChon)) {
                indexChieuThuc = i;
                break;
            }
        }

        // dùng thanh tẩy nếu AI chọn chiêu thanh tẩy
        if (indexChieuThuc == game.getAi().getListAllChieuThucTa().size() - 1) {
            return thanhTayController.aiThanhTay(game);
        }




        Player ai = game.getAi();
        String trangThaiAi = ai.getTrangThai();
        String hieuUngChieuThuc = ai.getListAllChieuThucTa().get(indexChieuThuc).getHieuUngChieuThuc();
        String kieuChiuThuc = ai.getListAllChieuThucTa().get(indexChieuThuc).getKieuChieuThuc();
        int mauAi = ai.getMau();
        int giapAi = ai.getGiap();

        Player ta = game.getPlayer();
        String trangThaiTa = ta.getTrangThai();
        int mauTa = ta.getMau();
        int giapTa = ta.getGiap();


        // tăng round lên 1
        game.setRound(game.getRound() + 1);
        // đổi người tấn công
        if (game.getNguoiTanCong() == 0) {
            game.setNguoiTanCong(1);
        } else {
            game.setNguoiTanCong(0);
        }

        // set hồi chiêu cho các chiêu thức của ta và AI
        for (int i = 0; i < ai.getListAllChieuThucTa().size(); i++) {
            if (i == ai.getListAllChieuThucTa().size() - 1) {
                if (ai.getListAllChieuThucTa().get(i).getCoolDown() > 0) {
                    ai.getListAllChieuThucTa().get(i).setCoolDown(ai.getListAllChieuThucTa().get(i).getCoolDown() - 1);
                } else {
                    ai.getListAllChieuThucTa().get(i).setCoolDown(0);
                }
                break;
            } else if (i == ai.getListAllChieuThucTa().size() - 2) {
                ai.getListAllChieuThucTa().get(i).setCoolDown(0);
                continue;
            }
            if (i != indexChieuThuc) {
                if (ai.getListAllChieuThucTa().get(i).getCoolDown() > 0) {
                    ai.getListAllChieuThucTa().get(i).setCoolDown(ai.getListAllChieuThucTa().get(i).getCoolDown() - 1);
                } else {
                    ai.getListAllChieuThucTa().get(i).setCoolDown(0);
                }
            } else {
                ai.getListAllChieuThucTa().get(indexChieuThuc).setCoolDown(6);
            }
        }
        for (int i = 0; i < ta.getListAllChieuThucTa().size(); i++) {
            if (i == ta.getListAllChieuThucTa().size() - 1) {
                if (ta.getListAllChieuThucTa().get(i).getCoolDown() > 0) {
                    ta.getListAllChieuThucTa().get(i).setCoolDown(ta.getListAllChieuThucTa().get(i).getCoolDown() - 1);
                } else {
                    ta.getListAllChieuThucTa().get(i).setCoolDown(0);
                }
                break;
            }
            if (ta.getListAllChieuThucTa().get(i).getCoolDown() > 0) {
                ta.getListAllChieuThucTa().get(i).setCoolDown(ta.getListAllChieuThucTa().get(i).getCoolDown() - 1);
            } else {
                ta.getListAllChieuThucTa().get(i).setCoolDown(0);
            }
        }

        // set trang thái mới tương ứng khi ta dùng chiêu thức
        if (hieuUngChieuThuc.equals(HieuUngChieuThuc.CHOANG.name())) {
            ta.setTrangThai(TrangThaiPlayer.CHOANG.name());
        } else if (hieuUngChieuThuc.equals(HieuUngChieuThuc.GIAM_DAME.name())) {
            ta.setTrangThai(TrangThaiPlayer.GIAM_DAME.name());
        } else {
            ta.setTrangThai(TrangThaiPlayer.GIAM_HOI_MAU.name());
        }



        // set máu, giáp của địch và ta sau khi dùng chiêu thức
        if (kieuChiuThuc.equals(KieuChieuThuc.TAN_CONG.name())) {
            if (!trangThaiAi.equals(TrangThaiPlayer.GIAM_DAME.name())) {
                int dameAi = ai.getListAllChieuThucTa().get(indexChieuThuc).getChiSo();
                if (giapTa > dameAi) {
                    ta.setGiap(giapTa - dameAi);
                } else {
                    ta.setMau(mauTa - (dameAi - ta.getGiap()));
                    ta.setGiap(0);
                }
            } else {
                int dameAi = ai.getListAllChieuThucTa().get(indexChieuThuc).getChiSo() / 2;
                if (giapTa > dameAi) {
                    ta.setGiap(giapTa - dameAi);
                } else {
                    ta.setMau(mauTa - (dameAi - ta.getGiap()));
                    ta.setGiap(0);
                }
            }
        } else if (kieuChiuThuc.equals(KieuChieuThuc.PHONG_THU.name())) {
            if ((100 - giapAi) < ai.getListAllChieuThucTa().get(indexChieuThuc).getChiSo()) {
                ai.setGiap(100);
            } else {
                ai.setGiap(ai.getGiap() + ai.getListAllChieuThucTa().get(indexChieuThuc).getChiSo());
            }
        } else {
            if (ai.getTrangThai().equals(TrangThaiPlayer.GIAM_HOI_MAU.name())){
                int mauHoi = ai.getListAllChieuThucTa().get(indexChieuThuc).getChiSo() / 2;
                if ((100 - mauAi) < mauHoi) {
                    ai.setMau(100);
                } else {
                    ai.setMau(ai.getMau() + mauHoi);
                }
            } else {
                int mauHoi = ai.getListAllChieuThucTa().get(indexChieuThuc).getChiSo();
                if ((100 - mauAi) < mauHoi) {
                    ai.setMau(100);
                } else {
                    ai.setMau(ai.getMau() + mauHoi);
                }
            }
        }

        // trả về đối tượng là game chưa các thông số mới sau khi AI dùng chiêu của ta và AI
        Game newGame = new Game(ta, ai);
        newGame.setRound(game.getRound());
        newGame.setNguoiTanCong(game.getNguoiTanCong());
        return newGame;
    }
}

import Models.ChieuThuc;
import Models.Enum.HieuUngChieuThuc;
import Models.Enum.KieuChieuThuc;
import Models.Enum.TrangThaiPlayer;
import Models.Game;
import Models.Player;
import Views.ViewStart;

import java.util.*;

public class Main {

    public static List<Integer> random5So() {
        // Tạo list chứa các số từ 0 đến 9
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        // Xáo trộn ngẫu nhiên
        Collections.shuffle(numbers);

        // Lấy 5 số đầu tiên
        return numbers.subList(0, 5);
    }

    public static void main(String[] args) {
        ChieuThuc[] arrChieuThuc = {
            new ChieuThuc("hoả công", HieuUngChieuThuc.GIAM_DAME.name(), KieuChieuThuc.TAN_CONG.name(), 20, 0),
            new ChieuThuc("băng công", HieuUngChieuThuc.CHOANG.name(), KieuChieuThuc.TAN_CONG.name(), 15, 0),
            new ChieuThuc("thuỷ công", HieuUngChieuThuc.GIAM_DAME.name(), KieuChieuThuc.TAN_CONG.name(), 10, 0),
            new ChieuThuc("giáp thổ", HieuUngChieuThuc.GIAM_HOI_MAU.name(), KieuChieuThuc.PHONG_THU.name(), 10, 0),
            new ChieuThuc("lôi điện", HieuUngChieuThuc.GIAM_HOI_MAU.name(), KieuChieuThuc.TAN_CONG.name(), 15, 0),
            new ChieuThuc("tường phong", HieuUngChieuThuc.CHOANG.name(), KieuChieuThuc.PHONG_THU.name(), 10, 0),
            new ChieuThuc("hồi huyết", HieuUngChieuThuc.GIAM_HOI_MAU.name(), KieuChieuThuc.HOI_MAU.name(), 10, 0),
            new ChieuThuc("đất lở", HieuUngChieuThuc.CHOANG.name(), KieuChieuThuc.TAN_CONG.name(), 10, 0),
            new ChieuThuc("cây đổ", HieuUngChieuThuc.GIAM_DAME.name(), KieuChieuThuc.TAN_CONG.name(), 10, 0),
            new ChieuThuc("bóng ma", HieuUngChieuThuc.GIAM_HOI_MAU.name(), KieuChieuThuc.PHONG_THU.name(), 10, 0)
        };

        ChieuThuc boLuotDich = new ChieuThuc("bỏ luợt", "", "", 0, 0);
        ChieuThuc thanhTayTa = new ChieuThuc("thanh tẩy", "", "", 0, 0);
        ChieuThuc thanhTayDich = new ChieuThuc("thanh tẩy", "", "", 0, 0);

        // Random 5 index
        List<Integer> keyRandom = random5So();

        // Chia làm 2 nhóm
        Map<Integer, ChieuThuc> listChieuThucDich = new HashMap<>();
        Map<Integer, ChieuThuc> listChieuThucTa = new HashMap<>();

        int indexDich = 0;
        int indexTa = 0;

        for (int i = 0; i < arrChieuThuc.length; i++) {
            if (keyRandom.contains(i)) {
                // Thuộc về địch
                listChieuThucDich.put(indexDich++, new ChieuThuc(arrChieuThuc[i]));
            } else {
                // Thuộc về ta
                listChieuThucTa.put(indexTa++, new ChieuThuc(arrChieuThuc[i]));
            }
        }

        listChieuThucDich.put(indexDich++, boLuotDich);
        listChieuThucDich.put(indexDich++, thanhTayDich);
        listChieuThucTa.put(indexTa++, thanhTayTa);

        Player player = new Player(100, 0);
        player.setListAllChieuThucTa(listChieuThucTa);
        player.setListAllChieuThucDich(listChieuThucDich);

        Player ai = new Player(100, 0);
        ai.setListAllChieuThucTa(listChieuThucDich);
        ai.setListAllChieuThucDich(listChieuThucTa);

        Game game = new Game(player, ai);

        new ViewStart(game);

    }
}

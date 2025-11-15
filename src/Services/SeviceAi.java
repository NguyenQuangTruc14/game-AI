package Services;

import Models.ChieuThuc;
import Models.Game;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class SeviceAi {
    public ChieuThuc chonChieuThuc(Map<Integer, ChieuThuc> listAllChieuThuc, Game game) {
        // random số int từ 0 đến 6
        Random random = new Random();
        List<ChieuThuc> listChieuThuc = List.copyOf(listAllChieuThuc.values());
        ChieuThuc chieuDuocChon = listChieuThuc.get(random.nextInt(listChieuThuc.size()));
        // return nhẫu nhiên một chiêu trong listAllChieuThuc mà không trả về null
        return chieuDuocChon;
    }
}

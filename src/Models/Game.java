package Models;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private Player player;
    private Player ai; // ở diagram là AI; ở đây tạm dùng Player cho đơn giản
    private int round;
    private int nguoiTanCong;
    // Danh sách tất cả chiêu trong game (id -> chiêu) nếu cần quản lý trung tâm
    private Map<Integer, ChieuThuc> listAllChieuThuc;

    public Game(Player player, Player ai) {
        this.player = player;
        this.ai = ai;
        this.round = 0;
        this.nguoiTanCong = 1; // ta là 1 địch là 0
        this.listAllChieuThuc = new HashMap<>();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getAi() {
        return ai;
    }

    public void setAi(Player ai) {
        this.ai = ai;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Map<Integer, ChieuThuc> getListAllChieuThuc() {
        return listAllChieuThuc;
    }

    public void setListAllChieuThuc(Map<Integer, ChieuThuc> listAllChieuThuc) {
        this.listAllChieuThuc = listAllChieuThuc;
    }

    public int getNguoiTanCong() {
        return nguoiTanCong;
    }

    public void setNguoiTanCong(int nguoiTanCong) {
        this.nguoiTanCong = nguoiTanCong;
    }
}

package Models;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private int mau; // HP
    private int giap; // armor / defense
    // Các chiêu sở hữu (key: id chiêu)
    private Map<Integer, ChieuThuc> listAllChieuThucTa;
    // Các chiêu đối thủ (ví dụ lưu chiêu đã biết của địch) — theo diagram có thể
    // dùng tương tự
    private Map<Integer, ChieuThuc> listAllChieuThucDich;
    private String trangThai; // trạng thái (ví dụ "normal", "stunned", ...)
    private ChieuThuc chieuAiVuaDung = new ChieuThuc("không có", "", "", 0, 0);


    public Player(int mau, int giap) {
        this.mau = Math.max(0, mau);
        this.giap = Math.max(0, giap);
        this.listAllChieuThucTa = new HashMap<>();
        this.listAllChieuThucDich = new HashMap<>();
        this.trangThai = "normal";
    }

    // Thêm chiêu cho bản thân
//    public void addChieuThucTac(int id, ChieuThuc chieu) {
//        listAllChieuThucTa.put(id, chieu);
//    }

    // Thêm chiêu của địch (thường dùng để lưu thông tin)
//    public void addChieuThucDich(int id, ChieuThuc chieu) {
//        listAllChieuThucDich.put(id, chieu);
//    }

    // Dùng chiêu của bản thân vào mục tiêu (đơn giản: trừ máu = chiSo -
    // target.giap, tối thiểu 0)
    // Trả về lượng sát thương thực tế gây ra, hoặc -1 nếu chiêu không thể dùng
    public int useChieuThucOnTarget(int chiieuId, Player target) {
        ChieuThuc ct = listAllChieuThucTa.get(chiieuId);
        if (ct == null)
            return -1;
        if (!ct.isAvailable())
            return -1;
        boolean used = ct.use();
        if (!used)
            return -1;
        int dmg = ct.executeEffect();
        int effective = Math.max(0, dmg - target.getGiap());
        target.takeDamage(effective);
        return effective;
    }

    // Nhận sát thương
    public void takeDamage(int damage) {
        if (damage <= 0)
            return;
        this.mau = Math.max(0, this.mau - damage);
    }

    // Tick cooldown cho toàn bộ chiêu của người chơi (gọi mỗi round)
    public void tickAllCooldowns() {
        for (ChieuThuc ct : listAllChieuThucTa.values())
            ct.tickCooldown();
        for (ChieuThuc ct : listAllChieuThucDich.values())
            ct.tickCooldown();
    }

    // Getters/Setters
    public int getMau() {
        return mau;
    }

    public void setMau(int mau) {
        this.mau = Math.max(0, mau);
    }

    public int getGiap() {
        return giap;
    }

    public void setGiap(int giap) {
        this.giap = Math.max(0, giap);
    }

    public Map<Integer, ChieuThuc> getListAllChieuThucTa() {
        return listAllChieuThucTa;
    }

    public Map<Integer, ChieuThuc> getListAllChieuThucDich() {
        return listAllChieuThucDich;
    }
    public void setListAllChieuThucTa(Map<Integer, ChieuThuc> listAllChieuThucTa) {
        this.listAllChieuThucTa = listAllChieuThucTa;
    }

    public void setListAllChieuThucDich(Map<Integer, ChieuThuc> listAllChieuThucDich) {
        this.listAllChieuThucDich = listAllChieuThucDich;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public ChieuThuc getChieuAiVuaDung() {
        return chieuAiVuaDung;
    }

    public void setChieuAiVuaDung(ChieuThuc chieuAiVuaDung) {
        this.chieuAiVuaDung = chieuAiVuaDung;
    }

    @Override
    public String toString() {
        return "Player{" + "mau=" + mau + ", giap=" + giap + ", trangThai='" + trangThai + '\'' + ", numTa="
                + listAllChieuThucTa.size() + ", numDich=" + listAllChieuThucDich.size() + '}';
    }

}

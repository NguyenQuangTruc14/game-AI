package Models;

import java.util.Objects;

public class ChieuThuc {
    private String ten;
    private String hieuUngChieuThuc;
    private int chiSo;
    private int coolDown;
    private int currentCooldown;

    public ChieuThuc(String ten, String hieuUngChieuThuc, int chiSo, int coolDown) {
        this.ten = ten;
        this.hieuUngChieuThuc = hieuUngChieuThuc;
        this.chiSo = chiSo;
        this.coolDown = Math.max(0, coolDown);
        this.currentCooldown = 0;
    }

    public boolean isAvailable() {
        return currentCooldown == 0;
    }

    public boolean use() {
        if (!isAvailable())
            return false;
        this.currentCooldown = this.coolDown;
        return true;
    }

    public void tickCooldown() {
        if (currentCooldown > 0)
            currentCooldown--;
    }

    public int executeEffect() {
        return chiSo;
    }

    // Getters / Setters
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHieuUngChieuThuc() {
        return hieuUngChieuThuc;
    }

    public void setHieuUngChieuThuc(String hieuUngChieuThuc) {
        this.hieuUngChieuThuc = hieuUngChieuThuc;
    }

    public int getChiSo() {
        return chiSo;
    }

    public void setChiSo(int chiSo) {
        this.chiSo = chiSo;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = Math.max(0, coolDown);
    }

    public int getCurrentCooldown() {
        return currentCooldown;
    }

    @Override
    public String toString() {
        return "ChieuThuc{" + "ten='" + ten + '\'' + ", hieuUng='" + hieuUngChieuThuc + '\'' + ", chiSo=" + chiSo
                + ", coolDown=" + coolDown + ", currentCooldown=" + currentCooldown + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ChieuThuc))
            return false;
        ChieuThuc that = (ChieuThuc) o;
        return chiSo == that.chiSo && coolDown == that.coolDown && Objects.equals(ten, that.ten);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ten, chiSo, coolDown);
    }
}

package Models;

import Models.Enum.HieuUngChieuThuc;

import java.util.Objects;

public class ChieuThuc {
    private String ten;
    private String hieuUngChieuThuc;
    private String kieuChieuThuc;
    private int chiSo;
    private int coolDown;

    public ChieuThuc(String ten, String hieuUngChieuThuc, String kieuChieuThuc, int chiSo, int coolDown) {
        this.ten = ten;
        this.hieuUngChieuThuc = hieuUngChieuThuc;
        this.kieuChieuThuc = kieuChieuThuc;
        this.chiSo = chiSo;
        this.coolDown = Math.max(0, coolDown);

    }

    public ChieuThuc(ChieuThuc other) {
        this.ten = other.ten;
        this.hieuUngChieuThuc = other.hieuUngChieuThuc;
        this.kieuChieuThuc = other.kieuChieuThuc;
        this.chiSo = other.chiSo;
        this.coolDown = other.coolDown;
    }


    public int executeEffect() {
        return chiSo;
    }

    public String getKieuChieuThuc() {
        return kieuChieuThuc;
    }

    public void setKieuChieuThuc(String kieuChieuThuc) {
        this.kieuChieuThuc = kieuChieuThuc;
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

    @Override
    public String toString() {
        return "ChieuThuc{" + "ten='" + ten + '\'' + ", hieuUng='" + hieuUngChieuThuc + '\'' + ", chiSo=" + chiSo
                + ", coolDown=" + coolDown + '}';
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

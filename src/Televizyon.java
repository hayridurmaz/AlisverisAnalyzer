/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Barış Comert
 * Televizyon objelerini temsil eden bu Class Urun Clas'ından türüyor.
 */
public class Televizyon extends Urun {

    String Ekran;
    int HdmiGirisi;
    boolean isSmart;

    public Televizyon(String Ekran, int HdmiGirisi, boolean isSmart, String Marka, String Model, double Fiyat) {
        // Construction methodu. Önce türediği class olan Urun Class'ına ait Constructer'ı çağırıyor.
        // ardından kendine has attribute'ları da ekliyor.
        super(Marka, Model, "televizyon", Fiyat);
        this.Ekran = Ekran;
        this.HdmiGirisi = HdmiGirisi;
        this.isSmart = isSmart;
    }

    // Getter ve setterlar
    public String getEkran() {
        return Ekran;
    }

    public void setEkran(String Ekran) {
        this.Ekran = Ekran;
    }

    public int getHdmiGirisi() {
        return HdmiGirisi;
    }

    public void setHdmiGirisi(int HdmiGirisi) {
        this.HdmiGirisi = HdmiGirisi;
    }

    public boolean isIsSmart() {
        return isSmart;
    }

    public void setIsSmart(boolean isSmart) {
        this.isSmart = isSmart;
    }

}

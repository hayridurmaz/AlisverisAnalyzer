/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Barış Cömert
 * Bilgisayar class'ı bilgisayar objelerini temsil ediyor. Bu class BilgisayarTelefon Class'ından türüyor.
 * O class da Urun Class'ından türediği için Her bilgisayar aynı zamandan bir Ürün.
 */
public class Bilgisayar extends BilgisayarTelefon {

    private String EkranKarti;
    private boolean hasSsd;

    public Bilgisayar(String EkranKarti, boolean hasSsd, String isletimSistemi, String Islemci, double Ram, String Marka, String Model, double Fiyat) {
        // Construction methodu. Önce türediği class olan BilgisayarTelefon Class'ına ait Constructer'ı çağırıyor.
        // ardından kendine has attribute'ları da ekliyor.
        super(isletimSistemi, Islemci, Ram, Marka, Model, "bilgisayar", Fiyat);
        this.EkranKarti = EkranKarti;
        this.hasSsd = hasSsd;
    }

    //seter ve getterlar
    public String getEkranKarti() {
        return EkranKarti;
    }

    public void setEkranKarti(String EkranKarti) {
        this.EkranKarti = EkranKarti;
    }

    public boolean getHasSsd() {
        return hasSsd;
    }

    public void setHasSsd(boolean hasSsd) {
        this.hasSsd = hasSsd;
    }

}

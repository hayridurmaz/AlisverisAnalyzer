/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Barış Cömert
 * Bilgisayar class'ı telefon objelerini temsil ediyor. Bu class BilgisayarTelefon Class'ından türüyor.
 * O class da Urun Class'ından türediği için Her telefon aynı zamandan bir Ürün.
 */
public class Telefon extends BilgisayarTelefon {

    private String EkranBoyutu;
    private String KameraCoz;

    public Telefon(String EkranBoyutu, String KameraCoz, String isletimSistemi, String Islemci, double Ram, String Marka, String Model, double Fiyat) {
        // Construction methodu. Önce türediği class olan BilgisayarTelefon Class'ına ait Constructer'ı çağırıyor.
        // ardından kendine has attribute'ları da ekliyor.
        super(isletimSistemi, Islemci, Ram, Marka, Model, "telefon", Fiyat);
        this.EkranBoyutu = EkranBoyutu;
        this.KameraCoz = KameraCoz;
    }

    // getters ve setterlar
    public String getEkranBoyutu() {
        return EkranBoyutu;
    }

    public void setEkranBoyutu(String EkranBoyutu) {
        this.EkranBoyutu = EkranBoyutu;
    }

    public String getKameraCoz() {
        return KameraCoz;
    }

    public void setKameraCoz(String KameraCoz) {
        this.KameraCoz = KameraCoz;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Barış Cömert
 * Bu class BilgisayarTelefon class'ı, Bilgisayar ve Telefon Objelerinin ortak özeliklerini temsil ediyor.
 * BilgisayarTelefon Objesi yaratılamasın diye abstract bir class. Urun class'ını extends ediyor, yani yaratılan
 * objeler aynı zamanda birer Urun objesi.
 */
abstract class BilgisayarTelefon extends Urun {

    private String isletimSistemi;
    private String Islemci;
    private double Ram;

    public BilgisayarTelefon(String isletimSistemi, String Islemci, double Ram, String Marka, String Model, String Kategori, double Fiyat) {
        // Construction methodu. Önce türediği class olan Ürün Class'ına ait Constructer'ı çağırıyor.
        // ardından kendine has attribute'ları da ekliyor.
        super(Marka, Model, Kategori, Fiyat);
        this.isletimSistemi = isletimSistemi;
        this.Islemci = Islemci;
        this.Ram = Ram;
    }

    //seter ve getterlar
    public String getIsletimSistemi() {
        return isletimSistemi;
    }

    public void setIsletimSistemi(String isletimSistemi) {
        this.isletimSistemi = isletimSistemi;
    }

    public String getIslemci() {
        return Islemci;
    }

    public void setIslemci(String Islemci) {
        this.Islemci = Islemci;
    }

    public double getRam() {
        return Ram;
    }

    public void setRam(double Ram) {
        this.Ram = Ram;
    }

}

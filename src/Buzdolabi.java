/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Barış Cömert
 * Buzdolabı class'ı Buzdolabı ürünlerini temsil ediyor. BeyazEşya Class'ını extend ediyot.
 */
public class Buzdolabi extends BeyazEsya {

    private double Yukseklik;
    private boolean isTekkapi;

    public Buzdolabi(double Yukseklik, boolean isTekkapi, String Renk, String EnerjiSınıfı, double Hacim, String Marka, String Model, double Fiyat) {
        // Construction methodu. Önce türediği class olan BeyazEsya Class'ına ait Constructer'ı çağırıyor.
        // ardından kendine has attribute'ları da ekliyor.
        super(Renk, EnerjiSınıfı, Hacim, Marka, Model, "buzdolabı", Fiyat);
        this.Yukseklik = Yukseklik;
        this.isTekkapi = isTekkapi;
    }

    // getter ve setterlar
    public double getYukseklik() {
        return Yukseklik;
    }

    public void setYukseklik(double Yukseklik) {
        this.Yukseklik = Yukseklik;
    }

    public boolean isIsTekkapi() {
        return isTekkapi;
    }

    public void setIsTekkapi(boolean isTekkapi) {
        this.isTekkapi = isTekkapi;
    }

}

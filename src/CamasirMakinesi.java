/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Barış Cömert
 * Çamaşır makinesi objelerini temsil ediyor. BeyazEsya class'ından türüyor.
 */
public class CamasirMakinesi extends BeyazEsya {

    private double Devir;
    private boolean isAkilli;

    // getter ve setterlar
    public double getDevir() {
        return Devir;
    }

    public void setDevir(double Devir) {
        this.Devir = Devir;
    }

    public boolean isIsAkilli() {
        return isAkilli;
    }

    public void setIsAkilli(boolean isAkilli) {
        this.isAkilli = isAkilli;
    }

    public CamasirMakinesi(double Devir, boolean isAkilli, String Renk, String EnerjiSınıfı, double Hacim, String Marka, String Model, double Fiyat) {
        // Construction methodu. Önce türediği class olan BeyazEsya Class'ına ait Constructer'ı çağırıyor.
        // ardından kendine has attribute'ları da ekliyor.
        super(Renk, EnerjiSınıfı, Hacim, Marka, Model, "camasir makinesi", Fiyat);
        this.Devir = Devir;
        this.isAkilli = isAkilli;
    }

}

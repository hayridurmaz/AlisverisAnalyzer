/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Barış Cömert
 * Bu class Bir Beyaz Eşyayı temsil ediyor. Urun klasından türüyor.
 * Abstract bir class çünkü beyaz eşya objesi yaratılsın istemiyorum.
 * Beyaz eşyanın altında Buzdolabı ve Çamaşır Makinesi vb. gibi objeler yaratılabiliyor.
 */
abstract class BeyazEsya extends Urun {

    //attributelar
    private String Renk;
    private String EnerjiSınıfı;
    private double Hacim;

    //seter ve getterlar
    public String getRenk() {
        return Renk;
    }

    public void setRenk(String Renk) {
        this.Renk = Renk;
    }

    public String getEnerjiSınıfı() {
        return EnerjiSınıfı;
    }

    public void setEnerjiSınıfı(String EnerjiSınıfı) {
        this.EnerjiSınıfı = EnerjiSınıfı;
    }

    public double getHacim() {
        return Hacim;
    }

    public void setHacim(double Hacim) {
        this.Hacim = Hacim;
    }

    public BeyazEsya(String Renk, String EnerjiSınıfı, double Hacim, String Marka, String Model, String Kategori, double Fiyat) {
        // Construction methodu
        super(Marka, Model, Kategori, Fiyat);
        this.Renk = Renk;
        this.EnerjiSınıfı = EnerjiSınıfı;
        this.Hacim = Hacim;
    }

}

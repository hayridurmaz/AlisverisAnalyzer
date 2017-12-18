
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Barış Cömert Urun Classı tüm objelerin üst class'ı. Tüm ürünler(bilgisayar, telefon vb.) Bu Class'tan türüyor.
 */
public class Urun {
    //Her objede olan özellikler

    private String Marka;
    private String Model;
    private String Kategori;
    private double Fiyat;
    ArrayList<Tweet> tweetler;

    public Urun(String Marka, String Model, String Kategori, double Fiyat) {
        //Constructer
        this.Marka = Marka;
        this.Model = Model;
        this.Kategori = Kategori;
        this.Fiyat = Fiyat;
        tweetler = new ArrayList<>();
    }

    //getter ve setterlar
    public void tweetEkle(Tweet tweet) {
        tweetler.add(tweet);
    }

    public ArrayList<Tweet> getTweetler() {
        return tweetler;
    }

    public String getKategori() {
        return Kategori;
    }

    public void setKategori(String Kategori) {
        this.Kategori = Kategori;
    }

    public String getMarka() {
        return Marka;
    }

    public void setMarka(String Marka) {
        this.Marka = Marka;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public double getFiyat() {
        return Fiyat;
    }

    public void setFiyat(double Fiyat) {
        this.Fiyat = Fiyat;
    }

    @Override
    public String toString() {
        //Her Ürünü String olarak döndürmeye yarayan method
        return Marka + " - " + Model + ":" + (int) Fiyat + " TL";
    }
}

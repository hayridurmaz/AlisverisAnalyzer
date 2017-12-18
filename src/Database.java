
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Barış Cömert. Bu Class Mongo veritabanına bağlanıyor, Ordaki verilerle etkileşime geçiyor. Veritabanı objelerini yönetiyor.
 */
public class Database {
    // Data decleration

    MongoClient mongoClient;
    DB db;
    DBCollection BILGISAYARLAR;
    DBCollection TELEFONLAR;
    DBCollection TVLER;
    DBCollection CAMASIR;
    DBCollection BUZDOLABI;

    public Database() {//Database Construction
        mongoClient = new MongoClient("127.0.0.1", 27017);
        db = mongoClient.getDB("database");
        BILGISAYARLAR = db.getCollection("BILGISAYARLAR");
        TELEFONLAR = db.getCollection("TELEFONLAR");
        TVLER = db.getCollection("TVLER");
        CAMASIR = db.getCollection("CAMASIR");
        BUZDOLABI = db.getCollection("BUZDOLABI");

    }

    public void AddBilgisayar(Bilgisayar bilgisayar) {//Database'e Bir bilgisayar eklemek
        BasicDBObject document = new BasicDBObject();
        document.put("Marka", bilgisayar.getMarka());
        document.put("Model", bilgisayar.getModel());
        document.put("Kategori", bilgisayar.getKategori());
        document.put("Fiyat", bilgisayar.getFiyat());
        document.put("isletimSistemi", bilgisayar.getIsletimSistemi());
        document.put("Islemci", bilgisayar.getIslemci());
        document.put("Ram", bilgisayar.getRam());
        document.put("EkranKarti", bilgisayar.getEkranKarti());
        document.put("hasSsd", bilgisayar.getHasSsd());
        BILGISAYARLAR.insert(document);
    }

    public void AddTelefon(Telefon t) {//Database'e Bir telefon eklemek
        BasicDBObject document = new BasicDBObject();
        document.put("Marka", t.getMarka());
        document.put("Model", t.getModel());
        document.put("Kategori", t.getKategori());
        document.put("Fiyat", t.getFiyat());
        document.put("IsletimSistemi", t.getIsletimSistemi());
        document.put("Islemci", t.getIslemci());
        document.put("Ram", t.getRam());
        document.put("EkranBoyut", t.getEkranBoyutu());
        document.put("KameraCoz", t.getKameraCoz());
        TELEFONLAR.insert(document);
    }

    public void AddTelevizyon(Televizyon t) {//Database'e Bir televizyon eklemek
        BasicDBObject document = new BasicDBObject();
        document.put("Marka", t.getMarka());
        document.put("Model", t.getModel());
        document.put("Kategori", t.getKategori());
        document.put("Fiyat", t.getFiyat());
        document.put("Ekran", t.getEkran());
        document.put("Hdmi", t.getHdmiGirisi());
        document.put("Smart", t.isIsSmart());
        TVLER.insert(document);
    }

    public void AddBuzdolabi(Buzdolabi b) {//Database'e Bir buzdolabı eklemek
        BasicDBObject document = new BasicDBObject();
        document.put("Marka", b.getMarka());
        document.put("Model", b.getModel());
        document.put("Kategori", b.getKategori());
        document.put("Fiyat", b.getFiyat());
        document.put("Renk", b.getRenk());
        document.put("EnerjiSinifi", b.getEnerjiSınıfı());
        document.put("Hacim", b.getHacim());
        document.put("Yukseklik", b.getYukseklik());
        document.put("isTekkapi", b.isIsTekkapi());
        BUZDOLABI.insert(document);
    }

    public void AddCamasir(CamasirMakinesi c) {//Database'e Bir çamaşır makinesi eklemek
        BasicDBObject document = new BasicDBObject();
        document.put("Marka", c.getMarka());
        document.put("Model", c.getModel());
        document.put("Kategori", c.getKategori());
        document.put("Fiyat", c.getFiyat());
        document.put("Renk", c.getRenk());
        document.put("EnerjiSinifi", c.getEnerjiSınıfı());
        document.put("Hacim", c.getHacim());
        document.put("Devir", c.getDevir());
        document.put("isAkilli", c.isIsAkilli());
        CAMASIR.insert(document);
    }

    public ArrayList<Urun> Urunler(String str) {//Bir kategorideki tüm ürünleri almak
        ArrayList<Urun> urunler = new ArrayList<>();

        List<DBObject> basics = db.getCollection(str).find().toArray();//tüm ütünleri arraye atıyor

        for (int i = 0; i < basics.size(); i++) {//o ürünleri arrayListte URUN objesi olarak tutuyor. Çünkü her objemiz temelde Birer ÜRÜN OBJESİ!!
            Urun urun = new Urun(basics.get(i).get("Marka").toString(), basics.get(i).get("Model").toString(), basics.get(i).get("Kategori").toString(), Double.parseDouble(basics.get(i).get("Fiyat").toString()));
            urunler.add(urun);
        }
        return urunler;
    }
}

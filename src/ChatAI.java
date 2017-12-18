
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Barış Cömert Bu class bir chat yapay zekası objesini tutuyor. Bu obje sayesinde rastlantısallık katılmış yapay zeka gerekli cevapları veriyor.
 *
 */
public class ChatAI {

    Database db;
    JTextArea txtChat;
    JTextField txtEnter;
    ArrayList<String> selamlama;
    ArrayList<String> halhatir;
    ArrayList<String> veda;
    ArrayList<String> anlasilmayan;

    public ChatAI(JTextField txtEnter, JTextArea txtChat, Database d) {
        // Construction methodu

        //declare attributelar
        this.txtChat = txtChat;
        this.txtEnter = txtEnter;
        db = d;
        selamlama = new ArrayList<String>(
                Arrays.asList("Merhaba size nasıl yardımcı olabilirim?", "Selamlar size nasıl yardımcı olabilirim?", "Hoşgeldiniz size nasıl yardımcı olabilirim?"));
        halhatir = new ArrayList<String>(
                Arrays.asList("İyiyim siz?", "Peki siz nasılsınız?"));
        veda = new ArrayList<String>(
                Arrays.asList("Güle güle", "Hoşçakalın", "Yeniden bekleriz"));
        anlasilmayan = new ArrayList<String>(
                Arrays.asList("Anlayamadım", "???", "Tekrarlayabilir misiniz?"));

        decideRandom(selamlama, txtChat);

        //girilen testi dinlemek için listener.
        txtEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Girilen texte göre gereken methodları çağırıyor.
                String uText = txtEnter.getText();
                txtChat.append("You: " + uText + "\n");//Kullanıcının yazısı
                txtEnter.setText("");
                //AI'nin yazısını seçtiği yer
                if (uText.contains("selam") || uText.contains("merhaba") || uText.contains("meraba")) {
                    decideRandom(selamlama, txtChat);
                } else if (uText.contains("görüş") || uText.contains("bye") || uText.contains("Hoşçakal")) {
                    decideRandom(veda, txtChat);
                } else if (uText.contains("nasılsın") || uText.contains("naber")) {
                    decideRandom(halhatir, txtChat);
                } else if (uText.contains("urun sececegim") || uText.contains("ürün seç")) {
                    kategoriSecme();
                } else if (uText.equals("1")) {
                    txtChat.append("AI: " + "Bilgisayarlara bakıyorum" + "\n");
                    try {
                        bilgisayarlar();
                    } catch (FileNotFoundException ex) {
                        UrunEkle.Error("Bir hata oluştu");
                    }
                } else if (uText.equals("2")) {
                    txtChat.append("AI: " + "Telefonlara bakıyorum" + "\n");
                    try {
                        telefonlar();
                    } catch (FileNotFoundException ex) {
                        UrunEkle.Error("Bir hata oluştu");
                    }
                } else if (uText.equals("3")) {
                    txtChat.append("AI: " + "Televizyonlara bakıyorum" + "\n");
                    try {
                        tvler();
                    } catch (FileNotFoundException ex) {
                        UrunEkle.Error("Bir hata oluştu");
                    }
                } else if (uText.equals("4")) {
                    txtChat.append("AI: " + "Çamaşır makinelerine bakıyorum" + "\n");
                    try {
                        camasirlar();
                    } catch (FileNotFoundException ex) {
                        UrunEkle.Error("Bir hata oluştu");
                    }
                } else if (uText.equals("5")) {
                    txtChat.append("AI: " + "Buzdolaplarına bakıyorum" + "\n");
                    try {
                        buzdolaplari();
                    } catch (FileNotFoundException ex) {
                        UrunEkle.Error("Bir hata oluştu");
                    }
                } else {
                    decideRandom(anlasilmayan, txtChat);
                }
            }
        });

    }

    public void kategoriSecme() {
        //Kullanıcıya kategori seçmesini söyleyecek olan method.
        txtChat.append("AI: " + "Elimizde aşağıdaki kategorilerde ürünler var" + "\n");
        txtChat.append("(1)Bilgisayar\n(2)Telefon\n(3)Televizyon\n(4)Çamaşır Makinesi\n(5)Buzdolabı\n");
    }

    public void decideRandom(ArrayList<String> messageList, JTextArea txtChat) {
        //İstenilen konuya göre random bir mesaj yazdıran method.
        int decider = (int) (Math.random() * messageList.size());
        txtChat.append("AI: " + messageList.get(decider) + "\n");
    }

    public void bilgisayarlar() throws FileNotFoundException {
        //bilgisayarları soran kullanıcıya veritabanından bilgisayar listesini çağırıp 
        //işlem yapan method.
        ArrayList<Urun> pcler = db.Urunler("BILGISAYARLAR");
        Urun maxUrun = urunTwitOrtalama(pcler);
        txtChat.append("AI: En beğenilen " + maxUrun.getKategori() + ": " + maxUrun.toString() + "\n");
    }

    public void telefonlar() throws FileNotFoundException {
        //telefon soran kullanıcıya veritabanından telefon listesini çağırıp 
        //işlem yapan method.
        ArrayList<Urun> teller = db.Urunler("TELEFONLAR");
        Urun maxUrun = urunTwitOrtalama(teller);
        txtChat.append("AI: En beğenilen " + maxUrun.getKategori() + ": " + maxUrun.toString() + "\n");
    }

    public void buzdolaplari() throws FileNotFoundException {
        //buzdolabı soran kullanıcıya veritabanından buzdolabı listesini çağırıp 
        //işlem yapan method.
        ArrayList<Urun> buzdolaplari = db.Urunler("BUZDOLABI");

        Urun maxUrun = urunTwitOrtalama(buzdolaplari);
        txtChat.append("AI: En beğenilen " + maxUrun.getKategori() + ": " + maxUrun.toString() + "\n");
    }

    public void camasirlar() throws FileNotFoundException {
        //çamaşır makinesi soran kullanıcıya veritabanından çamaşır makinesi listesini çağırıp 
        //işlem yapan method.
        ArrayList<Urun> camasirlar = db.Urunler("CAMASIR");

        Urun maxUrun = urunTwitOrtalama(camasirlar);

        txtChat.append("AI: En beğenilen " + maxUrun.getKategori() + ": " + maxUrun.toString() + "\n");
    }

    public void tvler() throws FileNotFoundException {
        //televizyon soran kullanıcıya veritabanından televizyon listesini çağırıp 
        //işlem yapan method.
        ArrayList<Urun> tvler = db.Urunler("TVLER");

        Urun maxUrun = urunTwitOrtalama(tvler);
        txtChat.append("AI: En beğenilen " + maxUrun.getKategori() + ": " + maxUrun.toString() + "\n");
    }

    public Urun urunTwitOrtalama(ArrayList<Urun> arr) throws FileNotFoundException {
        //Bir ürüne ait tweetlerin kutup değerlerinin ortalamasını alıyor, ona göre 
        //ürünün ne kadar sevildiğini anlıyor.
        txtChat.append("AI: Sistemdeki bütün " + arr.get(0).getKategori() + "'larin listesi\n");
        if (arr.isEmpty() || arr == null) {
            UrunEkle.Error("Bu kategoride ürün eklememişsiniz");
        }
        ArrayList<Double> ortalamalar = new ArrayList<>();

        //ArrayList <Tweet> maxtwler = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {//urunler listesinde gezinen array
            new TweetSearch(arr.get(i));
            ArrayList<Tweet> twler = arr.get(i).tweetler; //Her ürünün tweet listesi.
            System.out.println(arr.get(i).getMarka() + " - " + twler.size());//Her Marka model hakkında kaç tweet varsa konsola yazdırıyor.
            double sum = 0; //kutup ortalaması için sum ve count
            int count = 0;

            for (int j = 0; j < twler.size(); j++) {

                sum = sum + twler.get(j).kutup;
                count++;
            }
            double ort = sum / count;
            ortalamalar.add(ort);

            try {
                txtChat.append("AI: " + arr.get(i).toString() + "\n");//tüm ürünlerin yazdırıldığı yer
            } catch (Exception e) {
                UrunEkle.Error("Bir hata oluştu: " + e.toString());
            }

        }

        Urun maxUrun = arr.get(getMax(ortalamalar));//ortalaması en yüksek olan ürün

        return maxUrun;
    }

    public static int getMax(ArrayList<Double> arr) {
        //Bir arraylistin içindeki en büyük elemanın indexini veriyor.
        double max = arr.get(0);
        int maxindex = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) > max) {
                max = arr.get(i);
                maxindex = i;
            }
        }
        return maxindex;
    }

    /*public static void progress() {
        JFrame f = new JFrame("JProgressBar Sample");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container content = f.getContentPane();
        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(25);
        progressBar.setStringPainted(true);
        Border border = BorderFactory.createTitledBorder("Reading...");
        progressBar.setBorder(border);
        content.add(progressBar, BorderLayout.NORTH);
        f.setSize(300, 100);
        f.setVisible(true);
    }*/
}

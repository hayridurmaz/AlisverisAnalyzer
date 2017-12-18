
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Barış Cömert Bu class alınan her bir Tweet'i temsil ediyor. Tweetler comparable interface'ini implement ediyor, bu sayede de diğer tweetlerle kutup değerleri yönünden kıyaslanıyor.
 *
 */
public class Tweet implements Comparable<Tweet> {

    String yorum;
    double kutup;

    public Tweet(String yorum, double kutup) throws FileNotFoundException {
        //Consturct ediyor, Kutup sayısını bulacak methodu çağırıyor.
        this.yorum = yorum;
        this.kutup = kutup;
        setKutup();
    }

    public void setKutup() throws FileNotFoundException {
        //Tweet objesinin Kutup değerini bulan method.
        try {

            double sum = 0;
            String[] splited = yorum.split(" ");//yorumu kelimelere ayırmak

            for (int i = 0; i < splited.length; i++) {//her kelimeyi arıyor.
                BufferedReader br = new BufferedReader(new FileReader("src/senticnet4.txt"));//senticNet dosyası okumak için 
                //System.out.println(splited[i]);
                String line = br.readLine();//senticnet dosyasındaki kelimelere bakıyor
                while (line != null) {
                    line = line.trim();
                    int ilkBosluk = line.indexOf("	");//ilk boşluğa kadar kelime, son boşluktan sonrası kutup değeri; bkz. senticnet4.txt

                    String sozcuk = line.substring(0, ilkBosluk);
                    sozcuk = sozcuk.trim();

                    if (splited[i].equalsIgnoreCase(sozcuk)) {
                        int index = line.lastIndexOf("	") + 1;

                        String toBeParsed = line.substring(index);//kutup değeri 
                        toBeParsed = toBeParsed.trim();

                        if (UrunEkle.isDouble(toBeParsed)) {//alınan string numerical mı? kontrolü
                            double skor = Double.parseDouble(toBeParsed);
                            System.out.println(skor);
                            sum += skor;
                            System.out.println("**burda**");
                            System.out.println("Sum " + sum);
                            System.out.println("**burda**");

                        } else {
                            UrunEkle.Error("Bir double parse edilemedi.");
                        }
                        break;

                    }

                    line = br.readLine();//bir sonraki line oku.
                }

            }

            this.kutup = sum;

        } catch (Exception e) {
            e.printStackTrace();
            UrunEkle.Error("Bir hata oluştu" + e.toString());
        }

    }

    @Override
    public int compareTo(Tweet o) {
        return (int) (this.kutup * 1000 - o.kutup * 1000);//comparison
    }

}

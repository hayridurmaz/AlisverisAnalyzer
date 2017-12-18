
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Barış Cömert Twitterda bir konu üzerinde search yapmayı sağlayan objeleri barındıran Class.
 */
public class TweetSearch {

    public TweetSearch(Urun u) {
        //tweet arama methodu. Önce aramak için gerekli objeyi yaratıyor, sonra
        // search işini yapıyor
        ConfigurationBuilder cb = new ConfigurationBuilder();//twitter'a bağlanmak için configuration oluşturuyor.
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("bkwbbIid8D8OPj3U4cpbjjYw7")
                .setOAuthConsumerSecret("HnHfwQvmyw9Fi8MQOlTfaQyhCU76wWwUAR1bYEumZaju9W2bDH")
                .setOAuthAccessToken("291185104-XgjiIcnGq0KNo5VU8IbVugKlbMNw7FFnzXFZdNoI")
                .setOAuthAccessTokenSecret("wd4DVwBEU8usY9GLEZlT8h8SOs82S9yU2LqXA3suxeF3p");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        try {
            Query query = new Query(u.getMarka() + " " + u.getModel());//bir query oluştur

            QueryResult result;

            int count = 0;// Tüm tweetleri değil de ortalama 10 taneyi alabilmek için count
            do {

                result = twitter.search(query); //sorgunun response'u
                List<Status> tweets = result.getTweets(); // sonuç tweetleri listede tutuyor.

                for (Status tweet : tweets) {
                    if (!tweet.getLang().equals("en")) { //İngilizce olmayan tweetleri alma
                        continue;
                    }
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                    Tweet t = new Tweet(tweet.getText(), 0);//yeni tweet objesi yarat ve onu ilgili olduğu ürüne ekle
                    u.tweetEkle(t);
                    count++;
                }
                if (count >= 10) {//count 10dan büyükse çık
                    break;
                }
            } while ((query = result.nextQuery()) != null);

        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            UrunEkle.Error("Twitlere erişirken hata");
            System.exit(-1);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to search tweets: " + ex.getMessage());
            UrunEkle.Error("Twitlere erişirken hata");
        }
    }

}

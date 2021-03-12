import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DataCollection {
    private Document doc;

    public Document getDoc(){

        try {
            doc = Jsoup.connect("https://infotables.ru/meditsina/1198-koronavirus-v-rossii")
                    .userAgent("Chrome/86.0.4240.193")
                    .referrer("http://www.google.com")
                    .get();
        } catch (Exception e){
            e.printStackTrace();
        }
        return doc;
    }
}

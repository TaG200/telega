import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter {

    private Document doc;

    public Filter(Document doc){
        this.doc = doc;
    }

    public ArrayList<Element> getTable(){
        return doc.getElementsByTag("tbody").first().getElementsByTag("tr");
    }

    public String getData(ArrayList<Element> regionList, String SearchRegion){
        for (Element region : regionList) {
            String strRegion = region.toString();

            if (strRegion.contains(SearchRegion)){

                String officialRegion = null;
                String infectedOfAllTime = null;
                String infected = null;
                String died = null;
                String cured = null;

                Pattern pattern = Pattern.compile("<td>(\\d.\\d*)");
                Matcher matcher = pattern.matcher(strRegion);
                while (matcher.find()){
                    infectedOfAllTime = matcher.group(1);
                }

                pattern = Pattern.compile("color:#999\">(.*)<\\/span");
                matcher = pattern.matcher(strRegion);
                while (matcher.find()){
                    infected = matcher.group(1);
                }

                pattern = Pattern.compile("color:#99000b\">(\\d*)");
                matcher = pattern.matcher(strRegion);
                while (matcher.find()){
                    died = matcher.group(1);
                }

                pattern = Pattern.compile("color:#20975c\">(\\d*)");
                matcher = pattern.matcher(strRegion);
                while (matcher.find()){
                    cured = matcher.group(1);
                }

                pattern = Pattern.compile("<td>(\\W*)<\\/td>");
                matcher = pattern.matcher(strRegion);
                while (matcher.find()){
                    officialRegion = matcher.group(1);
                }

                return String.format("В регионе: %s \n" +
                        "Заболело за всё время: %s\n" +
                        "Умерло за всё время: %s\n" +
                        "Выздоровело за всё время: %s\n" +
                        "Прирост за сутки: %s", officialRegion, infectedOfAllTime, died, cured, infected);
            }
        }
        return null;
    }
}

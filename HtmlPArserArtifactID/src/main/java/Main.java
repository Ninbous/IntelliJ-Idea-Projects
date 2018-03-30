import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        String[] urls = {
                "https://dev5.epn.bz:30443/",
                "https://dev5.epn.bz:30443/ru/cashback"
        };

        for(int i = 0; i<urls.length;i++) {

            List<Articles> articlesList = new ArrayList<Articles>();

            Document doc = Jsoup.connect(urls[i]).get();

            Elements elements = doc.getElementsByAttributeValue("name", "description");

            for (Element e : elements) {
                String content = e.attr("content");

                articlesList.add(new Articles(content));
            }

            articlesList.forEach(System.out::println);
        }
    }
}

class Articles {
    private String name;

    public Articles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "name='" + name + '\'' +
                '}';
    }
}
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        String baseUrl = "https://dev5.tapicash.com:28444/es";

        String[] urls = {
                baseUrl + "/cashback",
                baseUrl + "/cashback/shops",
                baseUrl + "/cashback/flash-sale",
                baseUrl + "/cashback/actions ",
                baseUrl + "/cashback/coupons",
                baseUrl + "/cashback/vote",
                baseUrl + "/cashback/shops/category/127",
             //   baseUrl + "/cashback/shops/ozon/",
                baseUrl + "/cashback/shops/ali/",
                baseUrl + "/info/cashback-affiliate-program",
                baseUrl + "/search/cross-search",
             //   baseUrl + "/cashback/tracking",
                baseUrl + "/info/cashback-help",
                baseUrl + "/info/app-manual",
                baseUrl + "/info/getplugin",
           //     baseUrl + "/gifts",
            //    baseUrl + "/partners/ali/rules",
           //     baseUrl + "/partners/ozon/rules",
            //    baseUrl + "/partners",
                baseUrl + "/",
        };

        for (int i = 0; i < urls.length; i++) {

            List<Article> articleList = new ArrayList<Article>();
            List<Page> pageList = new ArrayList<>();

            /*
            String strUserId = "admin";
            String strPassword = "AdPass2017";

            String authString = strUserId + ":" + strPassword;

            String encodedString =
                    new String(Base64.encodeBase64(authString.getBytes()));
            */


            /*
            try {

                doc = Jsoup.connect(urls[i])
                        .header("Authorization", "Basic " + encodedString)
                        .get();

                //   System.out.println("Logged in using basic authentication");

            } catch (IOException e) {
                e.printStackTrace();
            }
            */

            Document doc = Jsoup.connect(urls[i]).get();


            Elements ogType = doc.getElementsByAttributeValue("property", "og:type");
            Elements ogTitle = doc.getElementsByAttributeValue("property", "og:title");
            Elements ogImage = doc.getElementsByAttributeValue("property", "og:image");

            String ogTypeContent = "";
            String ogTitleContent = "";
            String ogImageContent = "";

            for (Element aElem : ogType) {
                ogTypeContent = aElem.attr("content");
            }
            for (Element aElem : ogTitle) {
                ogTitleContent = aElem.attr("content");
            }
            for (Element aElem : ogImage) {
                ogImageContent = aElem.attr("content");
            }

            pageList.add(new Page(ogTypeContent, ogTitleContent, ogImageContent));

            System.out.print(urls[i] + " = ");
            pageList.forEach(System.out::println);
         //   System.out.print("}");

        }
    }
}

class Page {

    private String descriptionContent;
    private String linkNext;
    private String linkPrev;

    public Page(String descriptionContent, String linkNext, String linkPrev) {
        this.descriptionContent = descriptionContent;
        this.linkNext = linkNext;
        this.linkPrev = linkPrev;
    }

    public String getDescriptionContent() {
        return descriptionContent;
    }

    public void setDescriptionContent(String descriptionContent) {
        this.descriptionContent = descriptionContent;
    }

    public String getLinkNext() {
        return linkNext;
    }

    public void setLinkNext(String linkNext) {
        this.linkNext = linkNext;
    }

    public String getLinkPrev() {
        return linkPrev;
    }

    public void setLinkPrev(String linkPrev) {
        this.linkPrev = linkPrev;
    }

    @Override
    public String toString() {
        return "Page{" + "\n" +
                "og:type ='" + descriptionContent + '\'' + ",\n" +
                "og:title ='" + linkNext + '\'' + ",\n" +
                "og:image ='" + linkPrev + '\'' + "\n" +
                '}';
    }
}

class Article {

    private String name;


    public Article(String name) {
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
        return "{" +
                "content='" + name + '\'' +
                '}';
    }
}
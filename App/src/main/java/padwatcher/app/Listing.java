package padwatcher.app;

public class Listing {
    String title;
    String url;
    String price;
    String displayTitle;
    public Listing(String title, String url, String price) {
        this.title = title;
        this.url = url;
        this.price = price;
        this.displayTitle = cleanTitle(title);
    }

    public String cleanTitle(String title) {
        String[] titleParts = title.split("\\(");
        String ret = "";
        for (int i = 0; i < titleParts.length - 1; i++) {
            ret += titleParts[i];
        }
        return ret;
    }
}

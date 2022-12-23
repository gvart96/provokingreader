package md.gvart.provokingreader.engine.domain;

public record RssSubscription(
        int id,
        String title,
        String feedUrl,
        String logoUrl
) {

    public static RssSubscription intermediate(String title,
                                        String feedUrl,
                                        String logoUrl) {
        return new RssSubscription(-1, title,feedUrl,logoUrl);
    }

    public RssSubscription withId(int id) {
        return new RssSubscription(id, this.title,this.feedUrl,this.logoUrl);
    }
}

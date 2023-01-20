package md.gvart.provokingreader.engine.eventbus;

public enum Channel {
    SUBSCRIPTION_CREATE("rss.subscription.create"),
    SUBSCRIPTIONS_GET("rss.subscriptions.get"),
    SUBSCRIPTION_LIST("rss.subscription.list"),
    SUBSCRIPTION_SELECTED("rss.subscription.selected");

    private final String address;

    Channel(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}

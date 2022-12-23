package md.gvart.provokingreader.engine.eventbus;

public enum Channel {
    SUBSCRIPTION_CREATE("rss.subscription.create");

    private final String address;

    Channel(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}

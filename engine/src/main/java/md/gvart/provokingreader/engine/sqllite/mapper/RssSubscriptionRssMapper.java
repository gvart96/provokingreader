package md.gvart.provokingreader.engine.sqllite.mapper;

import io.vertx.sqlclient.Row;
import md.gvart.provokingreader.engine.domain.RssSubscription;

import java.util.function.Function;

public class RssSubscriptionRssMapper implements Function<Row, RssSubscription> {

    @Override
    public RssSubscription apply(Row row) {
        return new RssSubscription(
                row.getInteger("id"),
                row.getString("title"),
                row.getString("feed_url"),
                row.getString("logo_url"));
    }
}

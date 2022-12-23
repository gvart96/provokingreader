package md.gvart.provokingreader.engine.sqllite;

import com.google.inject.Inject;
import io.vertx.core.Future;
import io.vertx.sqlclient.SqlClient;
import io.vertx.sqlclient.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.gvart.provokingreader.engine.RssSubscriptionManager;
import md.gvart.provokingreader.engine.domain.RssSubscription;
import md.gvart.provokingreader.engine.sqllite.mapper.RssSubscriptionRssMapper;

import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class SqlLiteRssSubscriptionManager implements RssSubscriptionManager {

    private final SqlClient client;
    private final RssSubscriptionRssMapper rssSubscriptionRssMapper;

    @Override
    public Future<RssSubscription> save(RssSubscription subscription) {
        var params = Tuple.of(
                subscription.title(),
                subscription.feedUrl(),
                subscription.logoUrl()
        );
        return client.preparedQuery("INSERT INTO subscriptions (title,feed_url,logo_url) VALUES ($1,$2,$3)")
                .execute(params)
                .onFailure(it -> log.error("er",it))
                .map(it -> {
                    var id = it.iterator().next().getInteger(1);
                    return subscription.withId(id);
                });
    }


    @Override
    public Future<Void> delete(Long id) {
        return client.preparedQuery("DELETE from subscriptions WHERE id = $1")
                .execute(Tuple.of(id))
                .map(it -> null);


    }

    @Override
    public Future<List<RssSubscription>> findAll() {
        return client.query("SELECT * FROM subscriptions")
                .mapping(rssSubscriptionRssMapper)
                .execute()
                .onFailure(it -> log.error("Failed to retrieve subscriptions", it))
                .map(it -> StreamSupport.stream(it.spliterator(), false).toList());
    }
}

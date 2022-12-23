package md.gvart.provokingreader.engine;

import io.vertx.core.Future;
import md.gvart.provokingreader.engine.domain.RssSubscription;

import java.util.List;

public interface RssSubscriptionManager {

    Future<RssSubscription> save(RssSubscription subscription);
    Future<Void> delete(Long id);
    Future<List<RssSubscription>> findAll();
}

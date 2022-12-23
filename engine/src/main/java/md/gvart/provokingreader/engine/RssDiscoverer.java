package md.gvart.provokingreader.engine;

import io.vertx.core.Future;
import md.gvart.provokingreader.engine.domain.FeedItem;
import md.gvart.provokingreader.engine.domain.RssSubscription;

import java.util.List;


public interface RssDiscoverer {

    Future<RssSubscription> discover(String url);
    Future<List<FeedItem>> findAll(String url);

}

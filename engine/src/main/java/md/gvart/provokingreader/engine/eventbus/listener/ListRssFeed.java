package md.gvart.provokingreader.engine.eventbus.listener;

import com.google.inject.Inject;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.gvart.provokingreader.engine.RssDiscoverer;
import md.gvart.provokingreader.engine.RssSubscriptionManager;
import md.gvart.provokingreader.engine.eventbus.Channel;


@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class ListRssFeed extends AbstractVerticle {

    private final RssDiscoverer rssDiscoverer;

    @Override
    public void start() {
        vertx.eventBus().<JsonObject>consumer(Channel.SUBSCRIPTION_LIST.getAddress())
                .handler(it -> {
                    var url = it.body().getString("url");
                    rssDiscoverer.findAll(url)
                            .onSuccess(it::reply);
                });

    }
}

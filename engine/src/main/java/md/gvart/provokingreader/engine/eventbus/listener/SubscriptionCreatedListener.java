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
public class SubscriptionCreatedListener extends AbstractVerticle {

    private final RssSubscriptionManager subscriptionManager;
    private final RssDiscoverer rssDiscoverer;

    @Override
    public void start() {
        vertx.eventBus().<JsonObject>consumer(Channel.SUBSCRIPTION_CREATE.getAddress())
                .handler(it -> {
                    var url = it.body().getString("url");
                    rssDiscoverer.discover(url)
                            .compose(subscriptionManager::save)
                            .onSuccess(result -> log.info("Successfully created new subscription"));
                });

    }
}

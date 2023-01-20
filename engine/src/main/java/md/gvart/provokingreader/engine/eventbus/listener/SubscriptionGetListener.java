package md.gvart.provokingreader.engine.eventbus.listener;

import com.google.inject.Inject;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.gvart.provokingreader.engine.RssSubscriptionManager;
import md.gvart.provokingreader.engine.eventbus.Channel;


@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class SubscriptionGetListener extends AbstractVerticle {

    private final RssSubscriptionManager subscriptionManager;

    @Override
    public void start() {
        vertx.eventBus().consumer(Channel.SUBSCRIPTIONS_GET.getAddress())
                .handler(it -> subscriptionManager.findAll()
                        .onSuccess(it::reply));

    }
}

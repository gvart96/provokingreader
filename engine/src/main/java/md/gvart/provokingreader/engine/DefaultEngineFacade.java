package md.gvart.provokingreader.engine;

import com.google.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import md.gvart.provokingreader.engine.eventbus.EventBus;

@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class DefaultEngineFacade implements EngineFacade {

    private final RssDiscoverer rssDiscoverer;
    private final RssSubscriptionManager rssSubscriptionManager;
    private final EventBus eventBus;

    @Override
    public RssDiscoverer rssDiscoverer() {
        return rssDiscoverer;
    }

    @Override
    public RssSubscriptionManager rssSubscriptionManager() {
        return rssSubscriptionManager;
    }

    @Override
    public EventBus eventBus() {
        return eventBus;
    }
}

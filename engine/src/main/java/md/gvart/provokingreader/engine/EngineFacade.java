package md.gvart.provokingreader.engine;

import md.gvart.provokingreader.engine.eventbus.EventBus;

public interface EngineFacade {

    RssDiscoverer rssDiscoverer();

    RssSubscriptionManager rssSubscriptionManager();

    EventBus eventBus();
}

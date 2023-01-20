package md.gvart.xrssreader.config.di;

import com.google.inject.AbstractModule;
import md.gvart.provokingreader.engine.eventbus.EventBus;
import md.gvart.xrssreader.fxml.ComponentProvider;
import md.gvart.xrssreader.fxml.DefaultComponentProvider;
import md.gvart.xrssreader.view.FeedController;
import md.gvart.xrssreader.view.MainController;

public class DesktopModule extends AbstractModule {

    private final EventBus eventBus;

    public DesktopModule(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    protected void configure() {
        bind(EventBus.class).toInstance(eventBus);
        bind(MainController.class).asEagerSingleton();
        bind(FeedController.class).asEagerSingleton();
        bind(ComponentProvider.class).to(DefaultComponentProvider.class);
        bind(ComponentProvider.class).to(DefaultComponentProvider.class);
    }
}

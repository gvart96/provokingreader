package md.gvart.xrssreader.config.di;

import com.google.inject.AbstractModule;
import md.gvart.provokingreader.engine.eventbus.EventBus;
import md.gvart.xrssreader.fxml.ComponentProvider;
import md.gvart.xrssreader.fxml.DefaultComponentProvider;
import md.gvart.xrssreader.view.HelloController;

public class DesktopModule extends AbstractModule {

    private final EventBus eventBus;

    public DesktopModule(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    protected void configure() {
        bind(EventBus.class).toInstance(eventBus);
        bind(HelloController.class).asEagerSingleton();
        bind(ComponentProvider.class).to(DefaultComponentProvider.class);
    }
}

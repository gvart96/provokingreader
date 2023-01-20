package md.gvart.provokingreader.engine.factory;

import com.google.inject.Guice;
import io.vertx.core.Vertx;
import md.gvart.provokingreader.engine.EngineFacade;
import md.gvart.provokingreader.engine.eventbus.listener.SubscriptionCreatedListener;
import md.gvart.provokingreader.engine.sqllite.di.SqlLiteModule;

public class EngineFacadeFactory {

    public enum Type {
        LOCAL
    }

    public static EngineFacade create(Type type) {
        return switch (type) {
            case LOCAL -> setupLocalInfrastructure();
        };
    }

    private static EngineFacade setupLocalInfrastructure() {
        var injector = Guice.createInjector(new SqlLiteModule());

        return injector.getInstance(EngineFacade.class);
    }
}

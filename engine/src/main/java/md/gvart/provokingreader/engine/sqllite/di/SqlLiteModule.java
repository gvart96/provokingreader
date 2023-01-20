package md.gvart.provokingreader.engine.sqllite.di;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.jdbcclient.JDBCPool;
import io.vertx.sqlclient.SqlClient;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import md.gvart.provokingreader.engine.*;
import md.gvart.provokingreader.engine.context.registrar.VerticleRegistrar;
import md.gvart.provokingreader.engine.eventbus.EventBus;
import md.gvart.provokingreader.engine.eventbus.VertxEventBus;
import md.gvart.provokingreader.engine.eventbus.listener.ListRssFeed;
import md.gvart.provokingreader.engine.eventbus.listener.SubscriptionCreatedListener;
import md.gvart.provokingreader.engine.eventbus.listener.SubscriptionGetListener;
import md.gvart.provokingreader.engine.sqllite.SqlLiteRssSubscriptionManager;

@Slf4j
public class SqlLiteModule extends AbstractModule {

    @Override
    protected void configure() {
        var vertx = createVertx();
        var sqlClient = createSqlClient(vertx);
        runMigrations(sqlClient);
        Multibinder.newSetBinder(binder(), AbstractVerticle.class).addBinding().to(SubscriptionCreatedListener.class);
        Multibinder.newSetBinder(binder(), AbstractVerticle.class).addBinding().to(SubscriptionGetListener.class);
        Multibinder.newSetBinder(binder(), AbstractVerticle.class).addBinding().to(ListRssFeed.class);
        bind(Vertx.class).toInstance(vertx);
        bind(VerticleRegistrar.class).asEagerSingleton();
        bind(SqlClient.class).toInstance(sqlClient);
        bind(EventBus.class).to(VertxEventBus.class).in(Singleton.class);
        bind(EventBus.class).to(VertxEventBus.class).in(Singleton.class);
//        bind(SubscriptionCreatedListener.class).asEagerSingleton();
        bind(RssSubscriptionManager.class).to(SqlLiteRssSubscriptionManager.class).in(Singleton.class);
        bind(RssDiscoverer.class).to(DefaultRssDiscoverer.class).in(Singleton.class);
        bind(EngineFacade.class).to(DefaultEngineFacade.class).in(Singleton.class);
    }


    private Vertx createVertx() {
        return Vertx.vertx();
    }

    @SneakyThrows
    private void runMigrations(SqlClient sqlClient) {
        var query = new String((this.getClass().getClassLoader().getResourceAsStream("db/migration/v1_0_initial_schema.sql").readAllBytes()));
        sqlClient.query(query)
                .execute()
                .onFailure(it -> log.error("Failed to run migration script."))
                .result();
    }


    private JDBCPool createSqlClient(Vertx vertx) {
        return JDBCPool.pool(vertx, getConfig());
    }

    private JsonObject getConfig() {
        return JsonObject
                .of("url", "jdbc:sqlite:database_file",
                        "driver_class", "org.sqlite.jdbcDriver",
                        "max_pool_size", 5);
    }
}

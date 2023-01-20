package md.gvart.provokingreader.engine.context.registrar;

import com.google.inject.Inject;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class VerticleRegistrar implements Registrar<AbstractVerticle> {

    private final Vertx vertx;

    @Inject
    public VerticleRegistrar(Set<AbstractVerticle> verticles, Vertx vertx) {
        this.vertx = vertx;
        register(verticles);
    }

    @Override
    public void register(Set<AbstractVerticle> items) {
        items.forEach(it -> vertx.deployVerticle(it)
                .onSuccess(result -> log.info("Verticle deployed successfully {}", result)));
    }
}

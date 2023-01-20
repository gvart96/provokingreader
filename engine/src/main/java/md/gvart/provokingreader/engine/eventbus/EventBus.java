package md.gvart.provokingreader.engine.eventbus;

import io.vertx.core.Future;
import io.vertx.core.Handler;

import java.util.Map;

public interface EventBus {

    <T> Future<T> request(Channel channel, Map<String, Object> params);

    void publish(Channel channel, Map<String, Object> params);
    <T> void publish(Channel channel, T params);

    <T> void subscribe(Channel channel, Handler<T> handler);
}

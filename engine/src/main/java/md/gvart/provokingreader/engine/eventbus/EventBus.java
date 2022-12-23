package md.gvart.provokingreader.engine.eventbus;

import io.vertx.core.Future;

import java.util.Map;

public interface EventBus {

    <T> Future<T> request(Channel channel, Map<String, Object> params);

    void publish(Channel channel, Map<String, Object> params);
}

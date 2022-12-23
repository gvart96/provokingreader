package md.gvart.provokingreader.engine.eventbus;

import com.google.inject.Inject;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class VertxEventBus implements EventBus {

    private final Vertx vertx;

    @Override
    public <T> Future<T> request(Channel channel, Map<String, Object> params) {
        return vertx.eventBus().<T>request(channel.getAddress(), new JsonObject(params)).map(Message::body);
    }

    @Override
    public void publish(Channel channel, Map<String, Object> params) {
        vertx.eventBus().publish(channel.getAddress(), new JsonObject(params));
    }
}

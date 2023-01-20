package md.gvart.xrssreader.fxml;

import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class DefaultComponentProvider implements ComponentProvider {

    private final Injector injector;

    private ConcurrentMap<Class<?>, Object> componentCache = new ConcurrentHashMap<>();

    @SneakyThrows
    @Override
    public <T extends Parent> T provide(Class<T> clazz) {
        return (T) componentCache.computeIfAbsent(clazz, it -> loadView(it));
    }

    @SneakyThrows
    private <T extends Parent> Object loadView(Class<?> clazz) {
        var fileName = Arrays.stream(clazz.getSimpleName().split("(?=\\p{Upper})")).map(String::toLowerCase)
                .collect(Collectors.joining("-")) + ".fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(DefaultComponentProvider.class.getResource(fileName));
        fxmlLoader.setControllerFactory(it -> injector.getInstance(clazz));

        return fxmlLoader.<T>load();
    }

}

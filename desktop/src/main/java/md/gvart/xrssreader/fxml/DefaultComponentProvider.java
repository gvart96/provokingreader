package md.gvart.xrssreader.fxml;

import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Callback;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class DefaultComponentProvider implements ComponentProvider {

    private final Injector injector;

    @SneakyThrows
    @Override
    public <T> Parent provide(Class<T> clazz) {
        var fileName = Arrays.stream(clazz.getSimpleName().split("(?=\\p{Upper})")).map(String::toLowerCase)
                .collect(Collectors.joining("-")) + ".fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(DefaultComponentProvider.class.getResource(fileName));
        fxmlLoader.setControllerFactory(it -> injector.getInstance(clazz));
        return fxmlLoader.load();
    }
}

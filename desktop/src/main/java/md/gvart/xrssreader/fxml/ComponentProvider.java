package md.gvart.xrssreader.fxml;

import javafx.scene.Parent;

import java.util.function.Function;

public interface ComponentProvider  {

    <T extends Parent> T provide(Class<T> clazz);
}

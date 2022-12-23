package md.gvart.xrssreader.factory;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class StyledComponentFactory {

    public static <T extends Node> T create(T component, String... styles) {
        for (String style : styles) {
            component.getStyleClass().add(style);
        }
        return component;
    }
}

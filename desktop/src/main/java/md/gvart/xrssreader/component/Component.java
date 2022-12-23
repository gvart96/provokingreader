package md.gvart.xrssreader.component;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

public abstract class Component extends HBox {

    protected void append(Node node) {
        getChildren().add(node);
    }

    protected void addCssClass(String cssClass) {
        getStyleClass().add(cssClass);
    }

}

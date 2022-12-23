package md.gvart.xrssreader.component;

import io.vertx.core.Vertx;
import javafx.fxml.Initializable;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import md.gvart.provokingreader.engine.eventbus.Channel;
import md.gvart.provokingreader.engine.eventbus.EventBus;
import org.kordamp.ikonli.fontawesome.FontAwesome;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class AddSubscriptionComponent extends Component implements Initializable {

    public AddSubscriptionComponent(EventBus eventBus) {

        addCssClass("rss-feed-component");
        var wrapper = new HBox();

        wrapper.setOnMouseClicked(it -> {
            var dialog = new TextInputDialog();
            dialog.showAndWait().ifPresent(url ->
                    eventBus.publish(Channel.SUBSCRIPTION_CREATE, Map.of("url", url)));

        });

        wrapper.getStyleClass().add("icon-wrapper-dashed");
        var icon = FontIcon.of(FontAwesome.PLUS_SQUARE, 64);
        wrapper.getChildren().add(icon);
        append(wrapper);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

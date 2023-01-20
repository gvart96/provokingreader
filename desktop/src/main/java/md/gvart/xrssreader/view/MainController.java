package md.gvart.xrssreader.view;

import com.google.inject.Inject;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import md.gvart.provokingreader.engine.domain.RssSubscription;
import md.gvart.provokingreader.engine.eventbus.Channel;
import md.gvart.provokingreader.engine.eventbus.EventBus;
import md.gvart.xrssreader.component.AddSubscriptionComponent;
import md.gvart.xrssreader.fxml.ComponentProvider;
import md.gvart.xrssreader.view.navigation.NavigationMenuItemFactory;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController extends BorderPane implements Initializable {

    @FXML
    public StackPane contentArea;
    @FXML
    private FlowPane feedPane;

    @FXML
    private VBox navBar;
    @FXML
    private VBox subscriptions;

    @Inject
    private EventBus eventBus;
    @Inject
    private ComponentProvider provider;

    @FXML
    private MFXProgressSpinner spinner;

    private final ToggleGroup toggleGroup = new ToggleGroup();
    private final NavigationMenuItemFactory factory = new NavigationMenuItemFactory(toggleGroup);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var home = factory.createWithIcon("mfx-home", "Home", () -> {
        });

        MFXIconWrapper wrapper = new MFXIconWrapper("mfx-plus", 24, 32);
        var button = new MFXRectangleToggleNode("Subscribe", wrapper);
        button.setOnMouseClicked(it -> {
            var dialog = new TextInputDialog();
            dialog.showAndWait().ifPresent(url ->
                    eventBus.publish(Channel.SUBSCRIPTION_CREATE, Map.of("url", url)));
        });
        button.getStyleClass().add("btn-subscribe");
        navBar.getChildren().add(0, button);
        navBar.getChildren().add(1, home);
        eventBus.<List<RssSubscription>>request(Channel.SUBSCRIPTIONS_GET, Collections.emptyMap())
                .onSuccess(it -> {
                    it.forEach(subscription -> {
                                var btn = factory.createWithImage(subscription.logoUrl(), subscription.title(), () -> {
                                    contentArea.getChildren().clear();
                                    Parent controller = provider.provide(FeedController.class);
                                    contentArea.getChildren().add(controller);
                                    eventBus.publish(Channel.SUBSCRIPTION_SELECTED, subscription);
                                });
                                Platform.runLater(() -> {
                                    navBar.getChildren().add(btn);
                                });
                            }
                    );
                    Platform.runLater(() -> navBar.getChildren().remove(spinner));

                });


    }

}
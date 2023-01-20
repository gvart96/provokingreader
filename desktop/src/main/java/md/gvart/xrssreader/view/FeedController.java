package md.gvart.xrssreader.view;

import com.google.inject.Inject;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import md.gvart.provokingreader.engine.domain.RssSubscription;
import md.gvart.provokingreader.engine.eventbus.Channel;
import md.gvart.provokingreader.engine.eventbus.EventBus;

import java.net.URL;
import java.util.ResourceBundle;

public class FeedController extends HBox implements Initializable {

    @FXML
    private FlowPane content;

    @Inject
    private EventBus eventBus;

    private final SimpleObjectProperty<RssSubscription> selectedProperty = new SimpleObjectProperty<>();

    //todo check cache, always get reinitialized
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        selectedProperty.addListener((observable, oldValue, newValue) -> {
            System.out.println("it works");
            System.out.println(newValue);
            System.out.println(this.hashCode());
        });
        eventBus.subscribe(Channel.SUBSCRIPTION_SELECTED, selectedProperty::set);

    }

    public void setSelectedProperty(RssSubscription selectedProperty) {
        this.selectedProperty.set(selectedProperty);
    }
}
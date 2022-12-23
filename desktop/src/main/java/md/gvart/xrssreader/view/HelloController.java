package md.gvart.xrssreader.view;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import lombok.RequiredArgsConstructor;
import md.gvart.provokingreader.engine.eventbus.EventBus;
import md.gvart.xrssreader.component.AddSubscriptionComponent;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private FlowPane feedPane;

    @Inject
    private EventBus eventBus;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        feedPane.getChildren().add(new AddSubscriptionComponent(eventBus));
//
//        try {
////            feedPane.getChildren().addAll(loadFeed());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

  }
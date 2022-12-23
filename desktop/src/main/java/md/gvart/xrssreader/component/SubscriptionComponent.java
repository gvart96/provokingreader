package md.gvart.xrssreader.component;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import md.gvart.provokingreader.engine.domain.RssSubscription;
import md.gvart.xrssreader.factory.StyledComponentFactory;

public class SubscriptionComponent extends HBox {

    private final RssSubscription item;

    private final Label title;
    private final WebView description;
    public SubscriptionComponent(RssSubscription item) {
        this.item = item;
        title = StyledComponentFactory.create(new Label(item.title()),"");
        description = new WebView();



        this.getChildren().add(title);
        this.getChildren().add(description);
        this.getStyleClass().add("rss-feed-component");


    }


}


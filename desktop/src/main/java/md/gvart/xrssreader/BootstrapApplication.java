package md.gvart.xrssreader;

import com.google.inject.Guice;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import md.gvart.provokingreader.engine.factory.EngineFacadeFactory;
import md.gvart.xrssreader.config.di.DesktopModule;
import md.gvart.xrssreader.fxml.ComponentProvider;
import md.gvart.xrssreader.view.MainController;


public class BootstrapApplication extends Application {

    @Override
    public void start(Stage stage) {
        var engine = EngineFacadeFactory.create(EngineFacadeFactory.Type.LOCAL);
        var injector =Guice.createInjector(new DesktopModule(engine.eventBus()));
        var provider = injector.getInstance(ComponentProvider.class);
        Parent initialController = provider.provide(MainController.class);
        Scene scene = new Scene(initialController, 1024, 860);
        scene.getStylesheets().add(BootstrapApplication.class.getResource("styles/colors.css").toExternalForm());
        scene.getStylesheets().add(BootstrapApplication.class.getResource("styles/global.css").toExternalForm());
        scene.getStylesheets().add(BootstrapApplication.class.getResource("styles/rss-feed-component.css").toExternalForm());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
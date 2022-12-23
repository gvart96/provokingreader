module md.gvart.xrssreader {
    requires engine;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires io.vertx.core;
    requires com.google.guice;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome;
    requires MaterialFX;
    requires static lombok;
    opens md.gvart.xrssreader to javafx.fxml;
    exports md.gvart.xrssreader;
    exports md.gvart.xrssreader.view;
    exports md.gvart.xrssreader.component;
    opens md.gvart.xrssreader.view to javafx.fxml,com.google.guice;
    opens md.gvart.xrssreader.fxml to com.google.guice;



}
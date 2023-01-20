module engine {
    requires io.vertx.core;
    requires io.vertx.client.jdbc;
    requires io.vertx.client.sql;
    requires org.apache.logging.log4j;
    requires org.slf4j;
    requires static lombok;
    requires com.apptasticsoftware.rssreader;
    requires com.google.guice;
    requires org.xerial.sqlitejdbc;
    requires org.flywaydb.core;

    exports md.gvart.provokingreader.engine;
    exports md.gvart.provokingreader.engine.domain;
    exports md.gvart.provokingreader.engine.factory;
    exports md.gvart.provokingreader.engine.eventbus;


    opens md.gvart.provokingreader.engine;
    opens md.gvart.provokingreader.engine.eventbus.listener;
    opens md.gvart.provokingreader.engine.sqllite;
    opens md.gvart.provokingreader.engine.sqllite.mapper;
    opens db.migration;
    exports md.gvart.provokingreader.engine.context.registrar;
    opens md.gvart.provokingreader.engine.context.registrar;
}
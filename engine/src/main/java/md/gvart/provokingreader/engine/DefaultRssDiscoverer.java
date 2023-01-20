package md.gvart.provokingreader.engine;

import com.apptasticsoftware.rssreader.Image;
import com.apptasticsoftware.rssreader.RssReader;
import io.vertx.core.Future;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import md.gvart.provokingreader.engine.domain.FeedItem;
import md.gvart.provokingreader.engine.domain.RssSubscription;

import java.net.URI;
import java.net.URL;
import java.util.List;

@Slf4j
public class DefaultRssDiscoverer implements RssDiscoverer {

    private static final String TITLE_FALLBACK = "Title is not provided";
    private static final String DESCRIPTION_FALLBACK = "Description is not provided";
    private static final URI URL_FALLBACK = URI.create("fallback");
    private final RssReader reader;

    DefaultRssDiscoverer() {
        reader = new RssReader();
    }


    @Override
    public Future<List<FeedItem>> findAll(String url) {
        return Future.future(it -> readItems(url));
    }

    @SneakyThrows
    @Override
    public Future<RssSubscription> discover(String url) {
        var item = reader.read(url)
                .findFirst().map(it ->{
                    var channel = it.getChannel();
                    return RssSubscription.intermediate(
                            channel.getTitle(),
                            url,
                            channel.getLink() + "/favicon.ico"
                    );
                }).orElseThrow();

       return Future.succeededFuture(item);
    }

    @SneakyThrows
    private List<FeedItem> readItems(String url) {
        return reader.read(url)
                .map(
                        it -> new FeedItem(
                                it.getTitle().orElse(TITLE_FALLBACK),
                                it.getDescription().orElse(DESCRIPTION_FALLBACK),
                                it.getLink().map(URI::create).orElse(URL_FALLBACK)
                        )
                ).toList();
    }
}

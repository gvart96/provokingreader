package md.gvart.provokingreader.engine.domain;

import java.io.Serializable;
import java.net.URI;

public record FeedItem(
        String title,
        String description,
        URI uri
) implements Serializable {
}

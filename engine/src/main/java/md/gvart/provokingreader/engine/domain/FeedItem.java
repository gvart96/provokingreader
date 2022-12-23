package md.gvart.provokingreader.engine.domain;

import java.net.URI;
import java.net.URL;

public record FeedItem(
        String title,
        String description,
        URI uri
) { }

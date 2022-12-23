package md.gvart.provokingreader.engine.domain;


import java.time.LocalDateTime;

public record RssSubscriptionSummary(
        int totalItems,
        int newItems,
        LocalDateTime lastTimeRead
) {
}

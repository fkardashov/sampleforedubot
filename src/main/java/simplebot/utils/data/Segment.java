
package simplebot.utils.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "arrival",
        "from",
        "thread",
        "departure_platform",
        "departure",
        "stops",
        "departure_terminal",
        "to",
        "has_transfers",
        "tickets_info",
        "duration",
        "arrival_terminal",
        "start_date",
        "arrival_platform"
})
public class Segment {

    @JsonProperty("arrival")
    public String arrival;
    @JsonProperty("from")
    public From from;
    @JsonProperty("thread")
    public Thread thread;
    @JsonProperty("departure_platform")
    public String departurePlatform;
    @JsonProperty("departure")
    public String departure;
    @JsonProperty("stops")
    public String stops;
    @JsonProperty("departure_terminal")
    public Object departureTerminal;
    @JsonProperty("to")
    public To to;
    @JsonProperty("has_transfers")
    public Boolean hasTransfers;
    @JsonProperty("tickets_info")
    public TicketsInfo ticketsInfo;
    @JsonProperty("duration")
    public Integer duration;
    @JsonProperty("arrival_terminal")
    public Object arrivalTerminal;
    //@JsonProperty("start_date")
    public String start_date;
    @JsonProperty("arrival_platform")
    public String arrivalPlatform;
}

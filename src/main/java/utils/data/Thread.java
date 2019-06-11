
package utils.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uid",
        "title",
        "number",
        "short_title",
        "thread_method_link",
        "carrier",
        "transport_type",
        "vehicle",
        "transport_subtype",
        "express_type"
})
public class Thread {

    @JsonProperty("uid")
    public String uid;
    @JsonProperty("title")
    public String title;
    @JsonProperty("number")
    public String number;
    @JsonProperty("short_title")
    public String shortTitle;
    @JsonProperty("thread_method_link")
    public String threadMethodLink;
    @JsonProperty("carrier")
    public Carrier carrier;
    @JsonProperty("transport_type")
    public String transportType;
    @JsonProperty("vehicle")
    public Object vehicle;
    @JsonProperty("transport_subtype")
    public TransportSubtype transportSubtype;
    @JsonProperty("express_type")
    public Object expressType;

}

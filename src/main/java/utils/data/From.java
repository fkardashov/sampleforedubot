
package utils.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code",
    "title",
    "station_type",
    "popular_title",
    "short_title",
    "transport_type",
    "station_type_name",
    "type"
})
public class From {

    @JsonProperty("code")
    public String code;
    @JsonProperty("title")
    public String title;
    @JsonProperty("station_type")
    public String stationType;
    @JsonProperty("popular_title")
    public String popularTitle;
    @JsonProperty("short_title")
    public String shortTitle;
    @JsonProperty("transport_type")
    public String transportType;
    @JsonProperty("station_type_name")
    public String stationTypeName;
    @JsonProperty("type")
    public String type;

}

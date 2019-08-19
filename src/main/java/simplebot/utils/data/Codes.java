
package simplebot.utils.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "icao",
    "sirena",
    "iata"
})
public class Codes {

    @JsonProperty("icao")
    public Object icao;
    @JsonProperty("sirena")
    public Object sirena;
    @JsonProperty("iata")
    public Object iata;

}

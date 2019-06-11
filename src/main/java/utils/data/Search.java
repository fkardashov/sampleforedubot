
package utils.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "date",
    "to",
    "from"
})
public class Search {

    @JsonProperty("date")
    public String date;
    @JsonProperty("to")
    public To to;
    @JsonProperty("from")
    public From from;

}

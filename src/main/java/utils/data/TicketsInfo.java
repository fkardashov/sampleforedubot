
package utils.data;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "et_marker",
    "places"
})
public class TicketsInfo {

    @JsonProperty("et_marker")
    public Boolean etMarker;
    @JsonProperty("places")
    public List<Place> places = null;

}

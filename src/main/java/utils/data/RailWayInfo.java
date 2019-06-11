
package utils.data;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "interval_segments",
    "pagination",
    "segments",
    "search"
})
@Data
public class RailWayInfo {

    @JsonProperty("interval_segments")
    public List<Object> intervalSegments = null;
    @JsonProperty("pagination")
    public Pagination pagination;
    @JsonProperty("segments")
    public List<Segment> segments = null;
    @JsonProperty("search")
    public Search search;

}

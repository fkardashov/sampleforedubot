
package simplebot.utils.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cents",
    "whole"
})
public class Price {

    @JsonProperty("cents")
    public Integer cents;
    @JsonProperty("whole")
    public Integer whole;

}

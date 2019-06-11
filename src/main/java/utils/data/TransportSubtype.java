
package utils.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "color",
    "code",
    "title"
})
public class TransportSubtype {

    @JsonProperty("color")
    public String color;
    @JsonProperty("code")
    public String code;
    @JsonProperty("title")
    public String title;

}

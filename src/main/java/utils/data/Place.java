
package utils.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "currency",
    "price",
    "name"
})
public class Place {

    @JsonProperty("currency")
    public String currency;
    @JsonProperty("price")
    public Price price;
    @JsonProperty("name")
    public Object name;

}


package utils.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code",
    "contacts",
    "url",
    "logo_svg",
    "title",
    "phone",
    "codes",
    "address",
    "logo",
    "email"
})
public class Carrier {

    @JsonProperty("code")
    public Integer code;
    @JsonProperty("contacts")
    public String contacts;
    @JsonProperty("url")
    public String url;
    @JsonProperty("logo_svg")
    public Object logoSvg;
    @JsonProperty("title")
    public String title;
    @JsonProperty("phone")
    public String phone;
    @JsonProperty("codes")
    public Codes codes;
    @JsonProperty("address")
    public String address;
    @JsonProperty("logo")
    public String logo;
    @JsonProperty("email")
    public String email;

}

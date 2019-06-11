package utils.data;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@Data
public class Valute
{
    @XmlElement(name="CharCode")
    private String CharCode;
    @XmlElement(name="Value")
    private String Value;
    private String ID;
    @XmlElement(name="Nominal")
    private String Nominal;
    @XmlElement(name="NumCode")
    private String NumCode;
    @XmlElement(name="Name")
    private String Name;
}

package simplebot.utils.data;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ValCurs")
public class ValCurs {
    @XmlElement(name="name")
    private String name;
    @XmlElement(name="Date")
    private String Date;
    @XmlElement(name="Valute")
    private Valute[] Valute;

}

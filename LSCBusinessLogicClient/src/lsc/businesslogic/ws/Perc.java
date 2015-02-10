
package lsc.businesslogic.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for perc.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="perc">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="abs"/>
 *     &lt;enumeration value="perc"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "perc")
@XmlEnum
public enum Perc {

    @XmlEnumValue("abs")
    ABS("abs"),
    @XmlEnumValue("perc")
    PERC("perc");
    private final String value;

    Perc(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Perc fromValue(String v) {
        for (Perc c: Perc.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

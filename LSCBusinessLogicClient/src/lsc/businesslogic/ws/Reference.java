
package lsc.businesslogic.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for reference.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="reference">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="target"/>
 *     &lt;enumeration value="increment"/>
 *     &lt;enumeration value="decrement"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "reference")
@XmlEnum
public enum Reference {

    @XmlEnumValue("target")
    TARGET("target"),
    @XmlEnumValue("increment")
    INCREMENT("increment"),
    @XmlEnumValue("decrement")
    DECREMENT("decrement");
    private final String value;

    Reference(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Reference fromValue(String v) {
        for (Reference c: Reference.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

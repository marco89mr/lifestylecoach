
package lsc.businesslogic.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for function.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="function">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="average"/>
 *     &lt;enumeration value="sum"/>
 *     &lt;enumeration value="max"/>
 *     &lt;enumeration value="min"/>
 *     &lt;enumeration value="last"/>
 *     &lt;enumeration value="first"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "function")
@XmlEnum
public enum Function {

    @XmlEnumValue("average")
    AVERAGE("average"),
    @XmlEnumValue("sum")
    SUM("sum"),
    @XmlEnumValue("max")
    MAX("max"),
    @XmlEnumValue("min")
    MIN("min"),
    @XmlEnumValue("last")
    LAST("last"),
    @XmlEnumValue("first")
    FIRST("first");
    private final String value;

    Function(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Function fromValue(String v) {
        for (Function c: Function.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

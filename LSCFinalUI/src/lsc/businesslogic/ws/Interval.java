
package lsc.businesslogic.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for interval.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="interval">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="fixed"/>
 *     &lt;enumeration value="day"/>
 *     &lt;enumeration value="week"/>
 *     &lt;enumeration value="month"/>
 *     &lt;enumeration value="year"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "interval")
@XmlEnum
public enum Interval {

    @XmlEnumValue("fixed")
    FIXED("fixed"),
    @XmlEnumValue("day")
    DAY("day"),
    @XmlEnumValue("week")
    WEEK("week"),
    @XmlEnumValue("month")
    MONTH("month"),
    @XmlEnumValue("year")
    YEAR("year");
    private final String value;

    Interval(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Interval fromValue(String v) {
        for (Interval c: Interval.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

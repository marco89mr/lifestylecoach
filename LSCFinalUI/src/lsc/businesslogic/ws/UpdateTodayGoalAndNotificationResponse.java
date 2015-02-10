
package lsc.businesslogic.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for update_today_goal_and_notificationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="update_today_goal_and_notificationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="void" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "update_today_goal_and_notificationResponse", propOrder = {
    "_void"
})
public class UpdateTodayGoalAndNotificationResponse {

    @XmlElement(name = "void")
    protected int _void;

    /**
     * Gets the value of the void property.
     * 
     */
    public int getVoid() {
        return _void;
    }

    /**
     * Sets the value of the void property.
     * 
     */
    public void setVoid(int value) {
        this._void = value;
    }

}

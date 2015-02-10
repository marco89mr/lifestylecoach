
package lsc.businesslogic.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for compute_statisticResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="compute_statisticResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Statistic" type="{http://ws.businesslogic.lsc/}statistic" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "compute_statisticResponse", propOrder = {
    "statistic"
})
public class ComputeStatisticResponse {

    @XmlElement(name = "Statistic")
    protected Statistic statistic;

    /**
     * Gets the value of the statistic property.
     * 
     * @return
     *     possible object is
     *     {@link Statistic }
     *     
     */
    public Statistic getStatistic() {
        return statistic;
    }

    /**
     * Sets the value of the statistic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Statistic }
     *     
     */
    public void setStatistic(Statistic value) {
        this.statistic = value;
    }

}


package lsc.businesslogic.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for statistic complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="statistic">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.businesslogic.lsc/}base">
 *       &lt;sequence>
 *         &lt;element name="average" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cumulative" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="datas" type="{http://ws.businesslogic.lsc/}statisticData" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="field_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="from_date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="function" type="{http://ws.businesslogic.lsc/}function" minOccurs="0"/>
 *         &lt;element name="i_max" type="{http://ws.businesslogic.lsc/}statisticData" minOccurs="0"/>
 *         &lt;element name="i_min" type="{http://ws.businesslogic.lsc/}statisticData" minOccurs="0"/>
 *         &lt;element name="on_interval" type="{http://ws.businesslogic.lsc/}interval" minOccurs="0"/>
 *         &lt;element name="record_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="to_date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "statistic", propOrder = {
    "average",
    "cumulative",
    "datas",
    "fieldName",
    "fromDate",
    "function",
    "iMax",
    "iMin",
    "onInterval",
    "recordType",
    "toDate",
    "userId"
})
public class Statistic
    extends Base
{

    protected String average;
    protected String cumulative;
    protected List<StatisticData> datas;
    @XmlElement(name = "field_name")
    protected String fieldName;
    @XmlElement(name = "from_date")
    protected String fromDate;
    protected Function function;
    @XmlElement(name = "i_max")
    protected StatisticData iMax;
    @XmlElement(name = "i_min")
    protected StatisticData iMin;
    @XmlElement(name = "on_interval")
    protected Interval onInterval;
    @XmlElement(name = "record_type")
    protected String recordType;
    @XmlElement(name = "to_date")
    protected String toDate;
    protected int userId;

    /**
     * Gets the value of the average property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAverage() {
        return average;
    }

    /**
     * Sets the value of the average property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAverage(String value) {
        this.average = value;
    }

    /**
     * Gets the value of the cumulative property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCumulative() {
        return cumulative;
    }

    /**
     * Sets the value of the cumulative property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCumulative(String value) {
        this.cumulative = value;
    }

    /**
     * Gets the value of the datas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StatisticData }
     * 
     * 
     */
    public List<StatisticData> getDatas() {
        if (datas == null) {
            datas = new ArrayList<StatisticData>();
        }
        return this.datas;
    }

    /**
     * Gets the value of the fieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Sets the value of the fieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldName(String value) {
        this.fieldName = value;
    }

    /**
     * Gets the value of the fromDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * Sets the value of the fromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromDate(String value) {
        this.fromDate = value;
    }

    /**
     * Gets the value of the function property.
     * 
     * @return
     *     possible object is
     *     {@link Function }
     *     
     */
    public Function getFunction() {
        return function;
    }

    /**
     * Sets the value of the function property.
     * 
     * @param value
     *     allowed object is
     *     {@link Function }
     *     
     */
    public void setFunction(Function value) {
        this.function = value;
    }

    /**
     * Gets the value of the iMax property.
     * 
     * @return
     *     possible object is
     *     {@link StatisticData }
     *     
     */
    public StatisticData getIMax() {
        return iMax;
    }

    /**
     * Sets the value of the iMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatisticData }
     *     
     */
    public void setIMax(StatisticData value) {
        this.iMax = value;
    }

    /**
     * Gets the value of the iMin property.
     * 
     * @return
     *     possible object is
     *     {@link StatisticData }
     *     
     */
    public StatisticData getIMin() {
        return iMin;
    }

    /**
     * Sets the value of the iMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatisticData }
     *     
     */
    public void setIMin(StatisticData value) {
        this.iMin = value;
    }

    /**
     * Gets the value of the onInterval property.
     * 
     * @return
     *     possible object is
     *     {@link Interval }
     *     
     */
    public Interval getOnInterval() {
        return onInterval;
    }

    /**
     * Sets the value of the onInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Interval }
     *     
     */
    public void setOnInterval(Interval value) {
        this.onInterval = value;
    }

    /**
     * Gets the value of the recordType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordType() {
        return recordType;
    }

    /**
     * Sets the value of the recordType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordType(String value) {
        this.recordType = value;
    }

    /**
     * Gets the value of the toDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToDate() {
        return toDate;
    }

    /**
     * Sets the value of the toDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToDate(String value) {
        this.toDate = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     */
    public void setUserId(int value) {
        this.userId = value;
    }

}

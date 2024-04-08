
package webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for employee complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="employee">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employeeBasicDetails" type="{http://webservice/}employeeBasicDetails" minOccurs="0"/>
 *         &lt;element name="employeePermanentAddress" type="{http://webservice/}employeeAddress" minOccurs="0"/>
 *         &lt;element name="employeeTemporaryAddress" type="{http://webservice/}employeeAddress" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employee", propOrder = {
    "employeeBasicDetails",
    "employeePermanentAddress",
    "employeeTemporaryAddress"
})
public class Employee {

    protected EmployeeBasicDetails employeeBasicDetails;
    protected EmployeeAddress employeePermanentAddress;
    protected EmployeeAddress employeeTemporaryAddress;

    /**
     * Gets the value of the employeeBasicDetails property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeBasicDetails }
     *     
     */
    public EmployeeBasicDetails getEmployeeBasicDetails() {
        return employeeBasicDetails;
    }

    /**
     * Sets the value of the employeeBasicDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeBasicDetails }
     *     
     */
    public void setEmployeeBasicDetails(EmployeeBasicDetails value) {
        this.employeeBasicDetails = value;
    }

    /**
     * Gets the value of the employeePermanentAddress property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeAddress }
     *     
     */
    public EmployeeAddress getEmployeePermanentAddress() {
        return employeePermanentAddress;
    }

    /**
     * Sets the value of the employeePermanentAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeAddress }
     *     
     */
    public void setEmployeePermanentAddress(EmployeeAddress value) {
        this.employeePermanentAddress = value;
    }

    /**
     * Gets the value of the employeeTemporaryAddress property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeAddress }
     *     
     */
    public EmployeeAddress getEmployeeTemporaryAddress() {
        return employeeTemporaryAddress;
    }

    /**
     * Sets the value of the employeeTemporaryAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeAddress }
     *     
     */
    public void setEmployeeTemporaryAddress(EmployeeAddress value) {
        this.employeeTemporaryAddress = value;
    }

}

package com.restapi.model;
import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name="Shipping_Address")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private int aid;
    @Column(name="Country_Name")
    private String country;
    @Column(name="State")
    private String state;
    @Column(name="City")
    private String city;
    @Column(name="PinCode")
    private String pinCode;

}

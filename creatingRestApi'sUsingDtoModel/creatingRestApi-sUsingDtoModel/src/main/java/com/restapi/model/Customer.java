package com.restapi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Customer_Details")
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private int cid;
    @Column(name="Customer_name")
    private String name;
    @Column(name="Customer_password")
    private String password;
    @Column(name="Customer_email")
    private String email;
    @Column(name="Customer_mobileNumber")
    private String mobileNumber;
    @Column(name="Customer_Gender")
    private String gender;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
	
}

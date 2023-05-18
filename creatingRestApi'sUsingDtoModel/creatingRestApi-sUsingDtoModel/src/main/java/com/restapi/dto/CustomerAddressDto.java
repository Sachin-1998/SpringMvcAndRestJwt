package com.restapi.dto;

import com.restapi.model.Address;
import lombok.Data;
import javax.validation.constraints.*;
@Data
public class CustomerAddressDto
{
    @NotNull
    private int cid;
    @Size(min=3,max=20,message = "The characters must be between 3 to 20 !!")
    @NotBlank(message="the name should not be empty")
    private String name;
    @Size(min=9,max=20,message = "Password must be in between 9 to 20 Characters")
    private String password;
    @Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message="Invalid Mail Id ??" )
    private String email;
    @NotEmpty(message = "the Number should not be empty")
    private String mobileNumber;
    @NotBlank(message="the gender should not be empty")
    private String gender;
    private Address address;
	
}

package com.restapi.controller;


import com.restapi.dto.CustomerAddressDto;

import com.restapi.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
@RestController
@Validated
@RequestMapping("/customer/api")
public class MyController
{

    @Autowired
    private CustomerService customerService;
    @PostMapping("/customers")

    public CustomerAddressDto addCustomer(@Valid @RequestBody CustomerAddressDto customerAddressDto)
    {
        return customerService.addCustomer(customerAddressDto);
    }

    @GetMapping("/customers")
    public List<CustomerAddressDto> findAll()
    {
       return  customerService.getCustomerDetails();
    }
    @GetMapping("/customers/{id}")
    public CustomerAddressDto findById(@PathVariable int id)
    {
        return customerService.getById(id);
    }
    @DeleteMapping("/customers/{cid}")
    public void removeById(@PathVariable int cid)
    {
        customerService.deleteById(cid);
    }
    @PutMapping("/customers/{id}")
    public CustomerAddressDto updateById( @Valid @RequestBody CustomerAddressDto customerAddressDto,@PathVariable int id)
    {
       return  customerService.updateById(customerAddressDto,id);
    }

}

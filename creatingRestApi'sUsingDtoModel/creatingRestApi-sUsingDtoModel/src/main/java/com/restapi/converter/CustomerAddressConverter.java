package com.restapi.converter;

import com.restapi.dto.CustomerAddressDto;
import com.restapi.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class CustomerAddressConverter
{
    public CustomerAddressDto convertEntityToDto(Customer customer)
    {
        CustomerAddressDto customerAddressDto=new CustomerAddressDto();
        customerAddressDto.setCid(customer.getCid());
        customerAddressDto.setName(customer.getName());
        customerAddressDto.setEmail(customer.getEmail());
        customerAddressDto.setGender(customer.getGender());
        customerAddressDto.setMobileNumber(customer.getMobileNumber());
        customerAddressDto.setAddress(customer.getAddress());
        return customerAddressDto;

    }
    public List<CustomerAddressDto> convertEntityToDto(List<Customer> customer)
    {
        return customer .stream().map(x ->convertEntityToDto(x)).collect(Collectors.toList());
    }
    public Customer convertDtoToEntity(CustomerAddressDto customerAddressDto)
    {
        Customer c=new Customer();
        c.setCid(customerAddressDto.getCid());
        c.setName(customerAddressDto.getName());
        c.setEmail(customerAddressDto.getEmail());
        c.setGender(customerAddressDto.getGender());
        c.setMobileNumber(customerAddressDto.getMobileNumber());
        c.setAddress(customerAddressDto.getAddress());
        return c;
    }
    public List<Customer> convertDtoToEntity(List<CustomerAddressDto> customerAddressDto)
    {
        return customerAddressDto.stream().map(x ->convertDtoToEntity(x)).collect(Collectors.toList());
    }
}

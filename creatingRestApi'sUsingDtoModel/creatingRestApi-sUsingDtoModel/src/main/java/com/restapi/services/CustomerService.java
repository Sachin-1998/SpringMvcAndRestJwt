package com.restapi.services;

import com.restapi.Repository.CustomerRepository;
import com.restapi.converter.CustomerAddressConverter;
import com.restapi.dto.CustomerAddressDto;
import com.restapi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService
{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerAddressConverter converter;

    public CustomerAddressDto addCustomer(CustomerAddressDto customerAddressDto)
    {
        Customer customer= converter.convertDtoToEntity(customerAddressDto);
        customer=customerRepository.save(customer);
        return converter.convertEntityToDto(customer);
    }

    public List< CustomerAddressDto> getCustomerDetails()
    {
        List<Customer> getAll= customerRepository.findAll();
        return  converter.convertEntityToDto(getAll);
    }

    public CustomerAddressDto getById(int id)
    {
       Customer customer =customerRepository.findById(id).get();
        return converter.convertEntityToDto(customer);
    }
    public void deleteById(int id)
    {
        Customer customer=customerRepository.findById(id).get();
        converter.convertEntityToDto(customer);
        customerRepository.delete(customer);
    }
    public CustomerAddressDto updateById(CustomerAddressDto customerAddressDto,int id)
    {
        Customer customer1= converter.convertDtoToEntity(customerAddressDto);
        customer1=customerRepository.save(customer1);
        return converter.convertEntityToDto(customer1);
    }





}

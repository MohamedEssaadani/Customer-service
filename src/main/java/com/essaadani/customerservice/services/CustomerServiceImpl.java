package com.essaadani.customerservice.services;

import com.essaadani.customerservice.dto.CustomerRequestDTO;
import com.essaadani.customerservice.dto.CustomerResponseDTO;
import com.essaadani.customerservice.entities.Customer;
import com.essaadani.customerservice.mappers.CustomerMapper;
import com.essaadani.customerservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        /*
        * Mapping Objet Objet
        * Mapper les objets metier avec les objets UI
        * */

        //Customer customer = new Customer();
        //customer.setId(customerRequestDTO.getId());
        //customer.setName(customerRequestDTO.getName());
        //customer.setEmail(customerRequestDTO.getEmail());
        Customer customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO);
        Customer createdCustomer = customerRepository.save(customer);

        /*
         * Mapping Objet Objet
         * Mapper les objets metier avec les objets UI
         * */

        // CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        // customerResponseDTO.setId(createdCustomer.getId());
        // customerResponseDTO.setName(createdCustomer.getName());
        // customerResponseDTO.setEmail(createdCustomer.getEmail());

        return customerMapper.customerToCustomerResponseDTO(createdCustomer);
    }

    @Override
    public CustomerResponseDTO getCustomer(String id) {
        Customer customer = customerRepository.findById(id).get();

        return customerMapper.customerToCustomerResponseDTO(customer);
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO);
        Customer updatedCustomer = customerRepository.save(customer);

        return customerMapper.customerToCustomerResponseDTO(updatedCustomer);
    }

    @Override
    public List<CustomerResponseDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDTO> customerResponseDTOS
                = customers
                .stream()
                .map(customer -> customerMapper.customerToCustomerResponseDTO(customer))
                .collect(Collectors.toList());

        return customerResponseDTOS;
    }
}

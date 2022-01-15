package com.github.rodmotta.customerbackend;

import com.github.rodmotta.customerbackend.application.domain.Customer;
import com.github.rodmotta.customerbackend.application.domain.PageInfo;
import com.github.rodmotta.customerbackend.application.domain.Pagination;
import com.github.rodmotta.customerbackend.application.ports.CustomerRepositoryPort;
import com.github.rodmotta.customerbackend.application.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepositoryPort customerRepository;

    private Customer customer;

    private PageInfo pageInfo;

    private Pagination<Customer> pageCustomer;

    @BeforeEach
    public void setUp() {
        customer = new Customer(1L, "Rodrigo", "Motta",
                LocalDate.now(), "rodmottaads@gmail.com", "Software Developer");

        pageInfo = new PageInfo(1, 1, "id", "ASC");

        pageCustomer = new Pagination<>();
        pageCustomer.setContent(Arrays.asList(customer, customer));
        pageCustomer.setPageNumber(1);
        pageCustomer.setNumberOfElements(7);
        pageCustomer.setTotalPages(1);
        pageCustomer.setTotalElements(2L);
        pageCustomer.setFirst(true);
        pageCustomer.setLast(true);
    }

    @Test
    @DisplayName("Must find a customer by id.")
    public void mustFindACustomerById() {
        when(customerRepository.findById(anyLong())).thenReturn(customer);
        Customer customer = customerService.findById(anyLong());
        assertEquals(customer, this.customer);
    }

    @Test
    @DisplayName("Must find all customers.")
    public void mustFindAAllCustomers() {
        when(customerRepository.findAll(any(PageInfo.class))).thenReturn(pageCustomer);
        Pagination<Customer> pageCustomer = customerService.findAll(pageInfo);
        assertEquals(pageCustomer, this.pageCustomer);
    }

    @Test
    @DisplayName("Must find all customers by first name.")
    public void mustFindAAllCustomersByFirstName() {
        when(customerRepository.findByFirstName(anyString(), any(PageInfo.class))).thenReturn(pageCustomer);
        Pagination<Customer> pageCustomer = customerService.findByFirstName("Rodrigo", pageInfo);
        assertEquals(pageCustomer, this.pageCustomer);
    }

    @Test
    @DisplayName("Must find all customers by carrer.")
    public void mustFindAAllCustomersByCarrer() {
        when(customerRepository.findByCarrer(anyString(), any(PageInfo.class))).thenReturn(pageCustomer);
        Pagination<Customer> pageCustomer = customerService.findByCarrer("Developer",pageInfo);
        assertEquals(pageCustomer, this.pageCustomer);
    }

    @Test
    @DisplayName("Must save a customer successfully.")
    public void mustSaveACustomerSuccessfully() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer customer = customerService.save(this.customer);
        assertEquals(customer, this.customer);
    }

    @Test
    @DisplayName("Must update a customer successfully.")
    public void mustUpdateACustomerSuccessfully() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(customerRepository.findById(anyLong())).thenReturn(customer);
        Customer customer = customerService.update(anyLong(), this.customer);
        assertEquals(customer, this.customer);
    }

    @Test
    @DisplayName("Must delete a customer successfully.")
    public void mustDeleteACustomerSuccessfully() {
        doNothing().when(customerRepository).delete(anyLong());
        customerService.delete(anyLong());
        verify(customerRepository, times(1)).delete(anyLong());
    }
}

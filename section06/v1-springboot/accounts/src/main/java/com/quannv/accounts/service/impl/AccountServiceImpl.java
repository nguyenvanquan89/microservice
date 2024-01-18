package com.quannv.accounts.service.impl;

import com.quannv.accounts.constants.AccountsConstants;
import com.quannv.accounts.dto.AccountsDto;
import com.quannv.accounts.dto.CustomerDto;
import com.quannv.accounts.entity.Accounts;
import com.quannv.accounts.entity.Customer;
import com.quannv.accounts.exception.CustomerAlreadyExistsException;
import com.quannv.accounts.exception.ResourceNotFoundException;
import com.quannv.accounts.mapper.AccountsMapper;
import com.quannv.accounts.mapper.CustomerMapper;
import com.quannv.accounts.repository.AccountsRepository;
import com.quannv.accounts.repository.CustomerRepository;
import com.quannv.accounts.service.IAccountsService;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

  private AccountsRepository accountsRepository;
  private CustomerRepository customerRepository;

  /**
   * @param customerDto -CustomerDto object
   */
  @Override
  public void createAccount(CustomerDto customerDto) {
    Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
    Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(
        customerDto.getMobileNumber());
    if (optionalCustomer.isPresent()) {
      throw new CustomerAlreadyExistsException(
          "Customer already registered with given mobileNumber " + customerDto.getMobileNumber());
    }

    Customer saveCustomer = customerRepository.save(customer);

    accountsRepository.save(createNewAccount(saveCustomer));
  }

  /**
   * @param customer
   * @return the new account details
   */
  private Accounts createNewAccount(Customer customer) {
    Accounts newAccount = new Accounts();
    newAccount.setCustomerId(customer.getCustomerId());
    long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

    newAccount.setAccountNumber(randomAccNumber);
    newAccount.setAccountType(AccountsConstants.SAVINGS);
    newAccount.setBranchAddress(AccountsConstants.ADDRESS);

    return newAccount;
  }


  /**
   * @param mobileNumber
   * @return Accounts Details based on a given mobileNumber
   */
  @Override
  public CustomerDto fetchAccount(String mobileNumber) {
    Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
        () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
    );
    Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
        () -> new ResourceNotFoundException("Account", "customerId",
            customer.getCustomerId().toString())
    );
    CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
    customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
    return customerDto;
  }

  /**
   * @param customerDto
   * @return boolean indicating if the update of Account details is successful or not
   */
  @Override
  public boolean updateAccount(CustomerDto customerDto) {

    AccountsDto accountsDto = customerDto.getAccountsDto();
    if (accountsDto == null) {
      return false;
    }

    Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
        () -> new ResourceNotFoundException("Account", "AccountNumber",
            accountsDto.getAccountNumber().toString())
    );
    AccountsMapper.mapToAccounts(accountsDto, accounts);
    accounts = accountsRepository.save(accounts);

    Long customerId = accounts.getCustomerId();
    Customer customer = customerRepository.findById(customerId).orElseThrow(
        () -> new ResourceNotFoundException("Customer", "customerId", customerId.toString())
    );
    CustomerMapper.mapToCustomer(customerDto, customer);
    customerRepository.save(customer);

    return true;
  }

  /**
   * @param mobileNumber
   * @return boolean indicating if the delete of Account details is successful or not
   */
  @Override
  public boolean deleteAccount(String mobileNumber) {
    Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
        () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
    );
    accountsRepository.deleteByCustomerId(customer.getCustomerId());
    customerRepository.deleteById(customer.getCustomerId());

    return true;
  }


}

package com.quannv.accounts.service;

import com.quannv.accounts.dto.CustomerDto;


public interface IAccountsService {

  /**
   * @param customerDto -CustomerDto object
   */
  void createAccount(CustomerDto customerDto);

  /**
   *
   * @param mobileNumber
   * @return Accounts Details based on a given mobileNumber
   */
  CustomerDto fetchAccount(String mobileNumber);

  /**
   *
   * @param customerDto
   * @return boolean indicating if the update of Account details is successful or not
   */
  boolean updateAccount(CustomerDto customerDto);

  /**
   *
   * @param mobileNumber
   * @return boolean indicating if the delete of Account details is successful or not
   */
  boolean deleteAccount(String mobileNumber);

}

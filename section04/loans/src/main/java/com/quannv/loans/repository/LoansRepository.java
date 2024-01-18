package com.quannv.loans.repository;

import com.quannv.loans.entity.Loans;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoansRepository extends JpaRepository<Loans, Long> {
  Optional<Loans> findByMobileNumber(String mobileNumber);

  Optional<Loans> findByLoanNumber(String loanNumber);
}

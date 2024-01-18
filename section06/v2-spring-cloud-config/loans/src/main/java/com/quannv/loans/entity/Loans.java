package com.quannv.loans.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter @Getter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Loans extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "loan_id")
  private Long loanId;

  @Column(name = "mobile_number")
  private String mobileNumber;

  @Column(name = "loan_number")
  private String loanNumber;

  @Column(name = "loan_type")
  private String loanType;

  @Column(name = "total_loan")
  private int totalLoan;

  @Column(name = "amount_paid")
  private int amountPaid;

  @Column(name = "outstanding_amount")
  private int outstandingAmount;
}

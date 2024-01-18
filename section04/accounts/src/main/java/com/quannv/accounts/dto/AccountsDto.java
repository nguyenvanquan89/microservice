package com.quannv.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    name = "Accounts",
    description = "Schema to hold Account information"
)
public class AccountsDto {

  @Schema(
      description = "Account Number of QuanNv Bank account", example = "8923122334"
  )
  //@Size(min = 10, max = 10, message = "AccountNumber must be 10 digits")
  //@NotEmpty(message = "AccountNumber can not be a null or empty")
  //@Pattern(regexp = "(^$|[0-9]{10})", message = "AccountNumber must be 10 digits")
  private Long accountNumber;

  @Schema(
      description = "Account Type of QuanNv Bank account", example = "Savings"
  )
  @NotEmpty(message = "AccountType can not be a null or empty")
  private String accountType;

  @Schema(
      description = "Branch Address of QuanNv Bank account", example = "213 Kim Mã - Ba Đình - Hà Nội"
  )
  @NotEmpty(message = "BranchAddress can not be a null or empty")
  private String branchAddress;

}

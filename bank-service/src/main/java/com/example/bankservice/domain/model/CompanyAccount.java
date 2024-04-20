package com.example.bankservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(schema = "bank_service_schema")
public class CompanyAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyAccountId;

    private Long companyId;

    private Long employeeId;

    @NotNull(message = "This field cannot be NULL")
    private BigDecimal availableBalance;

    @NotNull(message = "This field cannot be NULL")
    private BigDecimal reservedAmount;

    @NotNull(message = "This field cannot be NULL")
    private Long creationDate;

    @NotNull(message = "This field cannot be NULL")
    private Long expireDate;

    @ManyToOne()
    @JoinColumn(name = "currencyId")
    private Currency currency;

    @NotNull(message = "This field cannot be NULL")
    private String accountNumber;

    @NotNull(message = "This field cannot be NULL")
    private boolean active;

}




















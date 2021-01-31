package com.fundsaccess.services.blueprint.domain.model;

import java.math.BigDecimal;

/**
 * Interface-definition for subservices
 */
public interface CalculatorService {

    BigDecimal calculate(String name);

}

package com.fundsaccess.services.blueprint.port.adapter.service;

import com.fundsaccess.services.blueprint.domain.model.CalculatorService;
import java.math.BigDecimal;
import javax.enterprise.context.ApplicationScoped;


/**
 * Implements a certain subservice
 */
@ApplicationScoped
public class CalculatorServiceMock implements CalculatorService {

    @Override
    public BigDecimal calculate(String name) {
        if (name.equals("Deep Thought")) {
            return BigDecimal.valueOf(42);
        }
        return BigDecimal.valueOf(0xCaffee);
    }

}

package com.fundsaccess.services.blueprint.domain.model;

import java.math.BigDecimal;

/**
 * Encapsulates the businesslogic
 *
 * Communicates with valueObjects or other entities
 */
public class Blueprint {

    private String name;
    private BigDecimal value;


    public Blueprint(String name) {
        this.name = name;
    }


    public String name() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public BigDecimal value() {
        return value;
    }

    public void changeValue(BigDecimal value) {
        this.value = value;
    }

}

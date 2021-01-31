package com.fundsaccess.services.blueprint.port.adapter.api.rest.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fundsaccess.services.blueprint.domain.model.CompactData;

import java.math.BigDecimal;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlueprintView {

    private String currency;
    private BigDecimal testValue;


    protected BlueprintView() {
        // FW-Ctor
    }

    public BlueprintView(CompactData blueprint) {
        this.currency = blueprint.dataSet.series.UNIT;
        // this.testValue = blueprint.value();
    }


    public String getCurrency() {
        return currency;
    }

    public BigDecimal getValue() {
        return testValue;
    }

}

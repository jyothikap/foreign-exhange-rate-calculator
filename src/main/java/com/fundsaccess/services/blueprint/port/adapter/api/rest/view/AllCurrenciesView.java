package com.fundsaccess.services.blueprint.port.adapter.api.rest.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fundsaccess.services.blueprint.domain.model.CompactData;

import java.math.BigDecimal;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AllCurrenciesView {

    private String currency;

    public AllCurrenciesView(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
}

package com.example.pattern.actions.register;

import com.example.pattern.entities.CustomerCa;

public class PerCustomerStrategy implements CustomerTypeStrategy {
    @Override
    public long getInfo(CustomerCa customerCa) {
        return customerCa.getId();
    }
}

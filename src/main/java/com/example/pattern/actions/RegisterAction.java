package com.example.pattern.actions;

import com.example.pattern.actions.register.EquipmentStrategy;
import com.example.pattern.entities.CustomerCa;
import com.example.pattern.entities.RequestCa;

public non-sealed class RegisterAction extends CaAction {
    protected RequestCa requestCa;
    protected CustomerCa customerCa;
    protected EquipmentStrategy equipmentStrategy;

    public ActionResponse handle() {
        return ActionResponse.getSuccess();
    }

    @Override
    public void passDataNextHandler() {
        assert (nextAction != null);
        this.nextAction.customerCa = customerCa;
        this.nextAction.requestCa = requestCa;
    }
}

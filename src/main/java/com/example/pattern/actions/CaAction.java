package com.example.pattern.actions;

import com.example.pattern.payloads.InputRegister;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public sealed abstract class CaAction permits ExtendAction, RegisterAction {
    protected RegisterAction nextAction;
    protected InputRegister inputRegister;

    public CaAction() {
    }

    public abstract ActionResponse handle();

    public ActionResponse doAction() {
        ActionResponse response = handle();

        if (handleActionResponse(response) && nextAction != null) {
            passDataNextHandler();
            response = nextAction.doAction();
        }

        return response;
    }

    public boolean handleActionResponse(ActionResponse actionResponse) {
        return actionResponse.getResponseCode().equals(ActionResponse.ResponseCode.SUCCESS);
    }

    public abstract void passDataNextHandler();

    public CaAction nextHandler(RegisterAction action) {
        this.nextAction = action;
        return this.nextAction;
    }
}

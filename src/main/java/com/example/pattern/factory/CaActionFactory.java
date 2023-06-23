package com.example.pattern.factory;

import com.example.pattern.PersistenceJPAConfig;
import com.example.pattern.actions.CaAction;
import com.example.pattern.actions.ExtendAction;
import com.example.pattern.actions.RegisterAction;

public class CaActionFactory {

    public static CaAction getCaAction(String type) {
        switch (type) {
            case "1" -> {
                return new RegisterAction();
            }
            case "2" -> {
                return new ExtendAction();
            }
            default -> {
                return null;
            }
        }
    }
}

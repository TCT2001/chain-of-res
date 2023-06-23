package com.example.pattern.actions;

public non-sealed class ExtendAction extends CaAction {

    @Override
    public ActionResponse handle() {
        return ActionResponse.getSuccess();
    }

    @Override
    public ActionResponse doAction() {

        return null;
    }

    @Override
    public void passDataNextHandler() {

    }
}

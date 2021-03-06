package com.train.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.*;
import com.train.shared.FieldVerifier;

public class MainPagePresenter extends
        Presenter<MainPagePresenter.MyView, MainPagePresenter.MyProxy> {

    @ProxyStandard
    @NameToken(nameToken)
    public interface MyProxy extends Proxy<MainPagePresenter>, Place {
    }

    public interface MyView extends View {
        String getName();

        Button getSendButton();

        void resetAndFocus();

        void setError(String errorText);
    }

    public static final String nameToken = "main";

    private final PlaceManager placeManager;

    @Inject
    public MainPagePresenter(EventBus eventBus, MyView view, MyProxy proxy,
                             PlaceManager placeManager) {
        super(eventBus, view, proxy);
        this.placeManager = placeManager;
    }

    @Override
    protected void onBind() {
        super.onBind();
        registerHandler(getView().getSendButton().addClickHandler(
                new ClickHandler() {
                    public void onClick(ClickEvent event) {
                        sendNameToServer();
                    }
                }));
    }

    @Override
    protected void onReset() {
        super.onReset();
        getView().resetAndFocus();
    }

    @Override
    protected void revealInParent() {
        RevealRootContentEvent.fire(this, this);
    }

    /**
     * Send the name from the nameField to the server and wait for a response.
     */
    private void sendNameToServer() {
        // First, we validate the input.
        getView().setError("");
        String textToServer = getView().getName();
        if (!FieldVerifier.isValidName(textToServer)) {
            getView().setError("Please enter at least four characters");
            return;
        }
        // Then, we transmit it to the ResponsePresenter, which will do the server
        // call
        placeManager.revealPlace(new PlaceRequest(ResponsePresenter.nameToken).with(
                ResponsePresenter.textToServerParam, textToServer));
    }

}

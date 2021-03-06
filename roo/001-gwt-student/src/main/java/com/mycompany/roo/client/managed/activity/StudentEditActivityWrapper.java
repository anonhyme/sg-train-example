package com.mycompany.roo.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.mycompany.roo.client.managed.request.ApplicationRequestFactory;
import com.mycompany.roo.client.proxy.StudentProxy;
import com.mycompany.roo.client.scaffold.activity.IsScaffoldMobileActivity;
import com.mycompany.roo.client.scaffold.place.ProxyEditView;
import com.mycompany.roo.client.scaffold.place.ProxyListPlace;
import com.mycompany.roo.client.scaffold.place.ProxyPlace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StudentEditActivityWrapper extends StudentEditActivityWrapper_Roo_Gwt {

    private final EntityProxyId<StudentProxy> proxyId;

    public StudentEditActivityWrapper(ApplicationRequestFactory requests, View<?> view, Activity activity, EntityProxyId<com.mycompany.roo.client.proxy.StudentProxy> proxyId) {
        this.requests = requests;
        this.view = view;
        this.wrapped = activity;
        this.proxyId = proxyId;
    }

    public Place getBackButtonPlace() {
        return (proxyId == null) ? new ProxyListPlace(StudentProxy.class) : new ProxyPlace(proxyId, ProxyPlace.Operation.DETAILS);
    }

    public String getBackButtonText() {
        return "Cancel";
    }

    public Place getEditButtonPlace() {
        return null;
    }

    public String getTitleText() {
        return (proxyId == null) ? "New Student" : "Edit Student";
    }

    public boolean hasEditButton() {
        return false;
    }

    @Override
    public String mayStop() {
        return wrapped.mayStop();
    }

    @Override
    public void onCancel() {
        wrapped.onCancel();
    }

    @Override
    public void onStop() {
        wrapped.onStop();
    }

    public interface View<V extends com.mycompany.roo.client.scaffold.place.ProxyEditView<com.mycompany.roo.client.proxy.StudentProxy, V>> extends ProxyEditView<StudentProxy, V> {
    }
}

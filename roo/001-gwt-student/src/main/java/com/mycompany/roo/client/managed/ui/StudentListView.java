package com.mycompany.roo.client.managed.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.text.client.DateTimeFormatRenderer;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.mycompany.roo.client.proxy.StudentProxy;
import com.mycompany.roo.client.scaffold.place.AbstractProxyListView;
import java.util.HashSet;
import java.util.Set;

public class StudentListView extends StudentListView_Roo_Gwt {

    private static final Binder BINDER = GWT.create(Binder.class);

    private static com.mycompany.roo.client.managed.ui.StudentListView instance;

    @UiField
    Button newButton;

    public StudentListView() {
        init(BINDER.createAndBindUi(this), table, newButton);
        table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
        init();
    }

    public static com.mycompany.roo.client.managed.ui.StudentListView instance() {
        if (instance == null) {
            instance = new StudentListView();
        }
        return instance;
    }

    public String[] getPaths() {
        return paths.toArray(new String[paths.size()]);
    }

    interface Binder extends UiBinder<HTMLPanel, StudentListView> {
    }
}

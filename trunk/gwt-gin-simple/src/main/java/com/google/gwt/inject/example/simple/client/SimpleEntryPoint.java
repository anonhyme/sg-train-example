/*
 * Copyright 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.gwt.inject.example.simple.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point to our simple Gin-instrumented example application.
 * <p/>
 * This entry point works just like any other GWT entry point. We use Gin here
 * to construct the widget with all its dependencies.
 */
public class SimpleEntryPoint implements EntryPoint {

//    private final SimpleGinjector ginjector = GWT.create(SimpleGinjector.class);

    public void onModuleLoad() {
        SimpleGinjector ginjector = GWT.create(SimpleGinjector.class);
        SimpleWidget widget = ginjector.getSimpleWidget();

        widget.showMessage();
        RootPanel.get().add(widget);
    }
}

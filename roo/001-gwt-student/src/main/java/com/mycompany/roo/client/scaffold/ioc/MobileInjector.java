package com.mycompany.roo.client.scaffold.ioc;

import com.mycompany.roo.client.scaffold.ScaffoldMobileApp;
import com.google.gwt.inject.client.GinModules;

@GinModules(value = {ScaffoldModule.class})
public interface MobileInjector extends ScaffoldInjector {

	ScaffoldMobileApp getScaffoldApp();
}

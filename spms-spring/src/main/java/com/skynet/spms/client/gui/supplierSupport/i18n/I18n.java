package com.skynet.spms.client.gui.supplierSupport.i18n;

import com.google.gwt.core.client.GWT;

public class I18n {
	private ModuleConst M = (ModuleConst) GWT.create(ModuleConst.class);
	private BaseConst B = (BaseConst) GWT.create(BaseConst.class);

	public ModuleConst getM() {
		return M;
	}

	public void setM(ModuleConst m) {
		M = m;
	}

	public BaseConst getB() {
		return B;
	}

	public void setB(BaseConst b) {
		B = b;
	}

}

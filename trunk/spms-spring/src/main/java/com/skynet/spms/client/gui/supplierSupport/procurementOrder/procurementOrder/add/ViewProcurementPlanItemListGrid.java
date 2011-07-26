package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.add;

import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ViewProcurementPlanItemListGrid extends FilterListGrid {

	public ViewProcurementPlanItemListGrid() {
		drawGrid();
	}

	public void drawGrid() {
		// ListGridField field1 = new ListGridField("itemNumber", "项号");
		ListGridField field2 = new ListGridField("partNumber", "件号");
		ListGridField field3 = new ListGridField("itemKeyword", "关键字");
		ListGridField field4 = new ListGridField("deliveryDate", "需求日期");
		field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		ListGridField field5 = new ListGridField("plannedQuantity", "计划采购数量");
		setFields(/* field1, */field2, field3, field4, field5);
	}
}
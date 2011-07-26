package com.skynet.spms.client.gui.partcatalog.technicalCatalog.indexInfomation;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomSelectItem;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.tools.ValidateUtils;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class IndexInfoAddWindow extends BaseWindow {

	public IndexInfoAddWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax,srcRect, listGrid, iconUrl);
	}

	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, final ListGrid listGrid) {
		IndexInfoListGrid indexInfoListGrid=(IndexInfoListGrid)listGrid;
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, IndexInfoAddWindow.this, -1);
			}
		});
	
		final DynamicForm form = new DynamicForm();
		form.setDataSource(indexInfoListGrid.getDataSource());
		form.setPadding(5);
		form.setWidth(300);
		form.setColWidths(100,200);
		
		final List<FormItem> itemList = new ArrayList<FormItem>();
		String required = "<font color=red>*</font>";
		
		//原厂商件号
		TextItem txtManufacturerNumber = new TextItem("manufacturerPartNumber"); 
		txtManufacturerNumber.setHint(required);
		txtManufacturerNumber.setRequired(true);
		//txtManufacturerNumber.setValidators(ValidateUtils.StringLenValidator(32, 32));
		txtManufacturerNumber.setValidateOnExit(true);
		txtManufacturerNumber.setWidth(200);
        itemList.add(txtManufacturerNumber);
        
        //商飞件号
        TextItem txtPartNumber = new TextItem("partNumber"); 
        //txtPartNumber.setHint(required);
        //txtPartNumber.setRequired(true);
        //txtPartNumber.setValidators(ValidateUtils.StringLenValidator(15, 15));
        //txtPartNumber.setValidateOnExit(true);
        txtPartNumber.setWidth(200);
        itemList.add(txtPartNumber);
        
        /*//超长零件号
        TextItem txtOverlengthNumber = new TextItem("overlengthPartNumber");  
        itemList.add(txtOverlengthNumber);
        //唯一部件标识号
        TextItem txtUniquePart = new TextItem("uniqueComponentIdentificationNumber");
        itemList.add(txtUniquePart);*/
        
        final SelectItem sltManufacturerCode = new CustomSelectItem(
        		"manufacturerCodeId", 
        		"partCatalog.technical", 
        		"manufacturerCodeForm_dataSource", 
        		"manufacturerCodeId", 
        		"manufacturerCode",
        		"manufacturerCode",
        		"manufacturerName");
        sltManufacturerCode.setPickListWidth(400);
        //sltManufacturerCode.setHint(required);
        //sltManufacturerCode.setRequired(true);
        sltManufacturerCode.setWidth(200);
        sltManufacturerCode.setAllowEmptyValue(true);
		itemList.add(sltManufacturerCode);
		
		FormItem[] items = new FormItem[itemList.size()];
        itemList.toArray(items);
        form.setFields(items);
        
        HLayout buttonPanel = new BtnsHLayout();
        buttonPanel.setHeight(40);
        IButton saveButton = new IButton();
        saveButton.setIcon("icons/save.png");  
	    saveButton.setTitle(ConstantsUtil.buttonConstants.saveButton());
	    saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(form.validate()){
				form.saveData();
				ShowWindowTools.showCloseWindow(srcRect, IndexInfoAddWindow.this, -1);
			}
			}
		});
	    
	    IButton cancelButton = new IButton();
	    cancelButton.setIcon("icons/remove.png");  
	    cancelButton.setTitle(ConstantsUtil.buttonConstants.cancelButton());
	    cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				form.reset();
			}
		});
	    
	    IButton helpButton = new IButton();
	    helpButton.setIcon("icons/book_help.png");  
	    helpButton.setTitle(ConstantsUtil.buttonConstants.helpButton());
	    helpButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});
	    
	    buttonPanel.addMember(saveButton);
	    buttonPanel.addMember(cancelButton);
	    buttonPanel.addMember(helpButton);
	    
	    HLayout tileGrid = new HLayout();
		tileGrid.setWidth100();
		tileGrid.setHeight("90%");
		//tileGrid.setTileWidth(150);
		//tileGrid.setTileHeight(150);
		tileGrid.setBorder("0px solid #9C9C9C");
		tileGrid.addChild(form);
		
		HLayout buttonGrid = new HLayout();
		buttonGrid.setWidth100();
		buttonGrid.setHeight("10%");
		//buttonGrid.setTileWidth(100);
		//buttonGrid.setTileHeight(100);	
		buttonGrid.setBorder("0px solid #9C9C9C");
		buttonGrid.addChild(buttonPanel);

		VLayout vLayout = new VLayout();
		vLayout.addMember(tileGrid); 
		vLayout.addMember(buttonGrid);
		return vLayout;
	}
}

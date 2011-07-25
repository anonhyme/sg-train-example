package com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.add;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.contractManagement.tag.TagEnum;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.model.BuybackPactModelLocator;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressForm;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.customerService.commonui.BaseRightListGrid;
import com.skynet.spms.client.gui.customerService.commonui.ContractProvisionPanel;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * 添加回购合同
 * 
 * @author fl
 * 
 */
public class BuyBackPactAddWin extends BaseWindow {

	public BuyBackPactAddWin(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	private BuybackPactModelLocator model;
	/** 申请单明细项表格 **/
	public BaseListGrid sheetItemGrid;
	/** 合同明细项表格 **/
	public BaseListGrid pactItemGrid;
	/** 合同明细项表单 **/
	public ContractItemAddForm pactItemForm;

	/**
	 * 回购申请明细项
	 * 
	 * @return
	 */
	public BaseListGrid getSheetItemGrid() {
		BaseListGrid lg = new BaseListGrid() {
			@Override
			public void drawGrid() {
				ListGridField fileld2 = new ListGridField("partNumber"/* , "件号" */);
				fileld2.setCanFilter(true);
				/** 数量 */
				ListGridField fileld5 = new ListGridField("quantity"/* , "数量" */);
				Utils.formatQuantityField(fileld5, "m_UnitOfMeasureCode");
				fileld5.setCanFilter(true);
				/** 单价 */
				ListGridField fileld6 = new ListGridField("unitPriceAmount");
				Utils.formatPriceField(fileld6, "currency");
				fileld6.setCanFilter(true);
				/** 金额 */
				ListGridField fileld7 = new ListGridField("price");
				Utils.formatPriceField(fileld7, "currency");
				fileld7.setCanFilter(true);
				setFields(fileld2, fileld5, fileld6, fileld7);
			}
		};
		lg.setLoadingDataMessage("数据加载中，请稍后......");
		return lg;
	}

	/**
	 * 回购合同明细表格
	 * 
	 * @return
	 */
	public BaseListGrid getPactItemGrid() {
		BaseListGrid lg = new BaseRightListGrid() {
			@Override
			public void drawGrid() {
				/** 件号 */
				ListGridField fileld2 = new ListGridField("partNumber");
				fileld2.setCanFilter(true);
				/** 数量 */
				ListGridField fileld5 = new ListGridField("quantity");
				Utils.formatQuantityField(fileld5, "m_UnitOfMeasureCode");
				fileld5.setCanFilter(true);
				/**
				 * 单价
				 */
				ListGridField fileld6 = new ListGridField("unitPrice");
				Utils.formatPriceField(fileld6, "currency");
				fileld6.setCanFilter(true);
				/**
				 * 金额
				 */
				ListGridField fileld7 = new ListGridField("price");
				Utils.formatPriceField(fileld7, "currency");
				fileld7.setCanFilter(true);

				setFields(fileld2, fileld5, fileld6, fileld7);
			}
		};
		lg.setLoadingDataMessage("数据加载中，请稍后......");
		return lg;
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		model = BuybackPactModelLocator.getInstance();
		VLayout mainLayout = new VLayout();
		mainLayout.setMembersMargin(15);
		pactItemGrid = getPactItemGrid();

		TabSet topTabSet = new TabSet();
		Tab baseTab = new Tab("合同基本信息");
		ContractBaseAddForm baseForm = new ContractBaseAddForm(pactItemGrid);
		baseTab.setPane(baseForm);

		Tab addressTab = new Tab("地址信息");
		addressTab.setPane(new BaseAddressForm(BaseAddressForm.Type.modify));

		Tab provisionTab = new Tab("合同条款");
		provisionTab.setPane(new ContractProvisionPanel(baseForm.form,
				TagEnum.RepairInspectClaimContractTemplate));

		Tab attachmentTab = new Tab("附件信息");
		attachmentTab.setPane(new AttachmentAddForm());
		topTabSet.setTabs(baseTab, addressTab, provisionTab, attachmentTab);

		topTabSet.setHeight(500);
		topTabSet.setWidth100();
		mainLayout.addMember(topTabSet);

		HLayout gridLayout = new HLayout();
		gridLayout.setHeight(200);
		VLayout sheetItemGridLayout = new VLayout();
		sheetItemGridLayout.setGroupTitle("回购申请明细项");
		sheetItemGridLayout.setIsGroup(true);
		sheetItemGrid = getSheetItemGrid();
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_BUYBACKSHEET,
				DSKey.C_BUYBACKSHEETITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						Criteria criteria = new Criteria();
						criteria.setAttribute("sheet.id", model.sheetId);
						sheetItemGrid.setDataSource(dataSource);
						sheetItemGrid.fetchData(criteria);
						sheetItemGrid.drawGrid();
						model.sheetItemGrid = sheetItemGrid;
					}
				});
		sheetItemGridLayout.addMember(sheetItemGrid);
		sheetItemGrid.addRecordClickHandler(new RecordClickHandler() {

			@Override
			public void onRecordClick(RecordClickEvent event) {
				ListGridRecord record = sheetItemGrid.getSelectedRecord();
				if (record != null) {
					pactItemForm.form.editNewRecord();
					pactItemForm.item_save.setTitle("保存");
					pactItemForm.item_no.setValue(record.getAttribute("partNumber"));
					pactItemForm.item_ManufacturerCode.setValue(record
							.getAttribute("m_ManufacturerCode.code"));
					pactItemForm.item_ManufacturerCodeId.setValue(record
							.getAttribute("m_ManufacturerCode.id"));
					pactItemForm.item_keys.setValue(record.getAttribute("keyword"));
					pactItemForm.item_count.setValue(record.getAttribute("quantity"));
					pactItemForm.item_unitPrice.setValue(record
							.getAttribute("unitPriceAmount"));
					pactItemForm.item_price.setValue(record.getAttribute("price"));
					pactItemForm.item_unit.setValue(record.getAttribute("m_UnitOfMeasureCode"));
					pactItemForm.item_currency.setValue(record.getAttribute("currency"));
				}
			}
		});
		gridLayout.addMember(sheetItemGridLayout);

		VLayout pactItemGridLayout = new VLayout();
		pactItemGridLayout.setGroupTitle("回购合同明细项");
		pactItemGridLayout.setIsGroup(true);

		dataSourceTool.onCreateDataSource(DSKey.C_BUYBACKPACT,
				DSKey.C_BUYBACKPACTITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						Criteria pactItemCriteria = new Criteria();
						pactItemCriteria.setAttribute("template.id", "-1");
						pactItemGrid.setDataSource(dataSource);
						pactItemGrid.setCriteria(pactItemCriteria);
						pactItemGrid.fetchData(pactItemCriteria);
						pactItemGrid.drawGrid();
						model.pactItemGrid = pactItemGrid;
					}
				});
		pactItemGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				pactItemForm.form.editSelectedData(pactItemGrid);
				pactItemForm.item_save.setTitle("修改");
			}
		});
		pactItemGridLayout.addMember(pactItemGrid);
		gridLayout.addMember(pactItemGridLayout);
		mainLayout.addMember(gridLayout);
		pactItemForm = new ContractItemAddForm("回购合同明细项");
		mainLayout.addMember(pactItemForm);
		return mainLayout;
	}
}

package com.skynet.spms.client.gui.customerService.salesService.SalesRequisitionSheet.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.salesService.SalesRequisitionSheet.model.RequisitionModelLocator;
import com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.business.SalesRequisitionSheetBusiness;
import com.skynet.spms.client.service.BaseCodeService;
import com.skynet.spms.client.service.BaseCodeServiceAsync;
import com.skynet.spms.client.vo.PartInfoVO;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
public class SalesRequisitionViewWindow extends BaseWindow {
	private RequisitionModelLocator modelLocator;

	private LayoutDynamicForm ldf;// 主订单Form

	private LayoutDynamicForm itemLdf;// 订单明细Form

	private BtnsHLayout btnsLayout;// 主订单操作按钮容器

	private BtnsHLayout itemBtnsLayout;// 明细操作按钮

	private IButton saveItemBtn;// 明细操作按钮

	private FormItem totalAmountItem;

	private FormItem item1;
	private DateItem item2;
	private SelectItem item3;
	private SelectItem item4;
	private FormItem item6;
	private FormItem item7;
	private SelectItem item8;
	private TextItem item9;
	private FormItem item10;
	private DicSelectItem item11;
	private SelectItem item12;
	private TextItem item13;
	private TextAreaItem item14;
	private FormItem item15;
	private FormItem item16;
	private FormItem keywordItem;//关键字
	private FormItem ataItem;//ATA章节号
	private SelectItem manufacturerItem;//制造商
	private FormItem partDescriptionItem;//件号描述
	private SelectItem certificateTypeItem;//证书类型

	private FormItem itemNumber;
	private String primaryKey;// 主表id

	private FilterListGrid quotationLG;// 报价单选择Grid

	private FilterListGrid itemListGrid;// 明细Grid
	
	private static BaseCodeServiceAsync service = GWT
	.create(BaseCodeService.class);

	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	public SalesRequisitionViewWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {

		modelLocator = RequisitionModelLocator.getInstance();
		primaryKey = modelLocator.primaryKey;
		/** 主面板* */
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();

		// /**面板1**/
		VLayout oneView = getPrimaryView();
		oneView.setHeight("35%");
		oneView.setWidth100();
		vmain.addMember(oneView);

		/** 面板2 * */
		HLayout twoView = getShowGridView();
		twoView.setHeight("30%");
		twoView.setWidth100();
		twoView.setLayoutTopMargin(10);
		vmain.addMember(twoView);

		/**面板3**/
		VLayout threeView = getItemView();
		// threeView.setHeight(180);
		threeView.setLayoutTopMargin(10);
		threeView.setHeight("35%");
		threeView.setWidth100();
		vmain.addMember(threeView);

		return vmain;
	}

	public void disableBtn() {
		this.btnsLayout.disable();
	}

	// 布局一
	private VLayout getPrimaryView() {
		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		v.setGroupTitle("订单信息");
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		ldf = new LayoutDynamicForm();
		ldf.setNumCols(6);
		ldf.setWidth100();
		ldf.setHeight100();
		ldf.setCellPadding(2);

		ldf.setDataSource(modelLocator.dataSource);
		// 根据id去获得信息
		Criteria criteria = new Criteria();
		criteria.setAttribute("id", primaryKey);
		ldf.fetchData(criteria);
		
		// 订单编号
		TextItem item_1 = new TextItem();
		item_1.setName("requisitionSheetNumber");
		item_1.setValue("业务编号系统自动生成");

		// 优先级
		DicSelectItem item_2 = new DicSelectItem();
		item_2.setName("m_Priority");

		// 机尾号
		SelectItem item_3 = new SelectItem();
		item_3.setName("airIdentificationNumber");
		Utils.setAirIdentificationNumberItemDS(item_3);

		// 联系人
		final TextItem item_6 = new TextItem();
		item_6.setName("linkman");

		// 联系方式
		final TextAreaItem item_8 = new TextAreaItem();
		item_8.setName("contactWay");
		item_8.setTitleVAlign(VerticalAlignment.TOP);
		item_8.setHeight("100%");
		item_8.setRowSpan(2);

		// 客户
		final SelectItem item_4 = new SelectItem();
		item_4.setName("m_CustomerIdentificationCode.id");
		CodeRPCTool.bindData(CodeRPCTool.CUSTOMERIDENTIFICATIONCODE, item_4);
		
		// 客户订单号
		TextItem item_7 = new TextItem();
		item_7.setName("customerPurchasingOrderNumber");

		// 发货地址
		// TextItem item_10 = new TextItem();
		// item_10.setName("m_ShippingAddress.address");

		// 总金额
		totalAmountItem = new SpinnerItem();
		totalAmountItem.setName("totalAmount");
		totalAmountItem.setValue("0.00");

		// 备注
		TextAreaItem item_9 = new TextAreaItem();
		item_9.setName("remark");
		item_9.setTitleVAlign(VerticalAlignment.TOP);
		item_9.setWidth("100%");
		item_9.setHeight("100%");
		item_9.setRowSpan(2);

		// 报价单id
		TextItem quotationIdItem = new TextItem();
		quotationIdItem.setName("quotationId");
		quotationIdItem.setVisible(false);

		
		
		ldf.setFields(item_1, item_2, item_3, item_4, item_6, item_7, item_8,
				item_9, totalAmountItem, quotationIdItem);
		
		Utils.setReadOnlyForm(ldf);

		v.setMembers(ldf);

		btnsLayout = new BtnsHLayout();

//		btnsLayout.addMember(saveBtn);
//		btnsLayout.addMember(cancelBtn);

		v.addMember(btnsLayout);

		return v;
	}

	// 布局2
	private HLayout getShowGridView() {
		HLayout h = new HLayout();
		h.setWidth100();
		h.setHeight100();
		h.setMembersMargin(5);
		h.setLayoutTopMargin(3);

		VLayout leftLayout = new VLayout();
		Label leftLb = new Label("报价单明细");
		leftLb.setHeight("20");
		leftLayout.addMember(leftLb);// 先放label
		leftLayout.addMember(getLeftGrid());// 再放Grid

		VLayout rightLayout = new VLayout();
		Label rightLb = new Label("客户订单明细");
		rightLb.setHeight("20");
		rightLayout.addMember(rightLb);
		rightLayout.addMember(getRightGrid());

		/** 根据是否来源报价单 显示报价单明细 **/
//		if(modelLocator.listGrid.getSelectedRecord()
//				.getAttribute("quotationId")==null||"".equals(modelLocator.listGrid.getSelectedRecord()
//				.getAttribute("quotationId"))){
//			h.addMember(leftLayout);
//		}
		
		h.addMember(rightLayout);
		return h;
	}

	// 布局3(明细添加)
	private VLayout getItemView() {
		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		v.setGroupTitle("客户订单明细");
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		itemLdf = new LayoutDynamicForm();
		itemLdf.setWidth100();
		itemLdf.setHeight100();
		itemLdf.setNumCols(6);
		itemLdf.setCellPadding(2);
		itemLdf.setMargin(5);

		itemLdf.setDataSource(modelLocator.itemDataSource);

		final TextItem primaryKeyItem = new TextItem();
		primaryKeyItem.setVisible(false);
		primaryKeyItem.setName("salesRequisitionSheet.id");

		// 报价件号(采购件号)
		item1 = Utils.getPartNumberList();
		item1.setName("partNumber");
		
		
		//关键字
		keywordItem = new TextItem();
		keywordItem.setName("keyword");
		
		//备件描述
		partDescriptionItem = new TextItem();
		partDescriptionItem.setName("partDescription");
		partDescriptionItem.setPrompt(partDescriptionItem.getValue()+"");
		
		//ATA
		ataItem= new TextItem();
		ataItem.setName("partAta");
		
		
		
		// 制造商
		manufacturerItem = new SelectItem();
		manufacturerItem.setName("m_ManufacturerCode.id");
		manufacturerItem.setTitle("制造商");
		manufacturerItem.setWidth(50);
		CodeRPCTool.bindData(CodeRPCTool.MANUFACTURERCODE, manufacturerItem);

		//证书类型
		certificateTypeItem = new SelectItem();
		certificateTypeItem.setName("m_CertificateType");
		certificateTypeItem.setMultiple(true);
		EnumTool.setValueMap(EnumTool.CERTIFICATETYPE, certificateTypeItem);

		// 交货日期
		item2 = new DateItem();
		item2.setName("partDeliveryDate");
		item2.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item2.setUseTextField(true);

		// 优先级
		item3 = new SelectItem();
		item3.setName("m_Priority");

		// 机尾号
		item4 = new SelectItem();
		item4.setName("airIdentificationNumber");
		Utils.setAirIdentificationNumberItemDS(item4);

		// 采购数量
		item6 = new SpinnerItem();
		item6.setName("quantity");
		item6.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				alculationAmount();
			}
		});

		// 特殊包装说明
		item7 = new TextItem();
		item7.setName("salesQuotationSheetItem.packageDescription");

		// 单位
		item8 = new SelectItem();
		item8.setName("m_UnitOfMeasureCode");

		// 特殊包装费用
		item9 = new TextItem();
		item9.setName("salesQuotationSheetItem.packagePrice");

		// 标准包装数量
		item10 = new TextItem();
		item10.setName("salesQuotationSheetItem.standardPackageQuantity");

		// 币种
		item11 = new DicSelectItem();
		item11.setName("m_InternationalCurrencyCode");

		// 采购单价
		item13 = new TextItem();
		item13.setName("unitOfPrice");

		// 制造商
		// item12 = new SelectItem();
		// item12.setTitle("制造商");
		// item12.setName("m_ManufacturerCode.id");
		//		
		// CodeRPCTool.bindData(CodeRPCTool.MANUFACTURERCODE, item12);

		// 采购金额
		item15 = new TextItem();
		item15.setName("amount");

		// 付款要求
		item14 = new TextAreaItem();
		item14.setName("salesQuotationSheetItem.paymentRequirement");
		item14.setHeight("100%");
		item14.setTitleVAlign(VerticalAlignment.TOP);

		// 备注
		item16 = new TextAreaItem();
		item16.setName("remarkText");
		item14.setHeight("100%");
		item16.setTitleVAlign(VerticalAlignment.TOP);

		// 项号
		itemNumber = new TextItem();
		itemNumber.setName("itemNumber");
		itemNumber.setVisible(false);


		
		
		itemLdf.setFields(primaryKeyItem, item1,keywordItem,partDescriptionItem,ataItem,manufacturerItem,certificateTypeItem, item2, item3, item4, item6,
				item7, item8, item9, item10, item11, item13, item15, item14,
				item16, itemNumber);

		Utils.setReadOnlyForm(itemLdf);
		
		v.addMember(itemLdf);

//		itemBtnsLayout.setMembers(saveItemBtn, cleanBtn, closeBtn);
//		v.addMember(itemBtnsLayout);

		return v;
	}

	// 选择Grid
	private ListGrid getLeftGrid() {
		quotationLG = new FilterListGrid();

		Utils.setListGridHeight(quotationLG);

		// 初始化订单明细数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_SALESQUOTATIONSHEET_ITEM,
				DSKey.C_SALESQUOTATIONSHEET_ITEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						// 指定报价单数据源
						String id = modelLocator.listGrid.getSelectedRecord()
								.getAttribute("quotationId");
						quotationLG.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.setAttribute("id", id);
						criteria.setAttribute("_r", String.valueOf(Math
								.random()));
						quotationLG.fetchData(criteria);


						// 报价编号
						ListGridField field2 = new ListGridField(
								"salesQuotationSheet.quotationSheetNumber");

						// 报价件号
						ListGridField field3 = new ListGridField(
								"quotationPartNumber");
						
						ListGridField keywordField = new ListGridField("keyword"/* , "关键字" */);
						keywordField.setPrompt(field2.getAttribute("keyword"));

						// 报价数量
						ListGridField field4 = new ListGridField("quantity");
						Utils.formatQuantityField(field4);

						// 报价单价
						ListGridField field5 = new ListGridField(
								"unitPriceAmount");
						Utils.formatPriceField(field5);

						// 交货期
						ListGridField field6 = new ListGridField(
								"deliveryLeadTime");

						quotationLG.setFields( field2, field3,keywordField, field4,
								field5, field6);

					}
				});

		// 选中行事件
		quotationLG.addCellClickHandler(new CellClickHandler() {

			public void onCellClick(CellClickEvent event) {
				// 先清空Form表单的值
				itemLdf.clearValues();

				itemLdf.editNewRecord();

				Utils.makeAllNotSelectLG(itemListGrid);

				// 再冲赋值
				ListGridRecord lgr = event.getRecord();
				setFormItemValues(lgr);

				itemNumber.setValue(itemListGrid.getRecords().length + 1);

			}

		});

		return quotationLG;
	}

	// 订单明细项
	private ListGrid getRightGrid() {
		itemListGrid = new FilterListGrid();
		Utils.setListGridHeight(itemListGrid);
		itemListGrid.setDataSource(modelLocator.itemDataSource);
		// 获得询价单明细项
		Criteria criteria = new Criteria();
		criteria.setAttribute("id", primaryKey);
		criteria.setAttribute("_r", String.valueOf(Math.random()));
		itemListGrid.fetchData(criteria);

		
		ListGridField field2 = new ListGridField("partNumber"/* , "件号" */);

		ListGridField field3 = new ListGridField("quantity"/* , "数量" */);
		Utils.formatQuantityField(field3);

		ListGridField field4 = new ListGridField("unitOfPrice"/* , "单价" */);
		Utils.formatPriceField(field4);

		ListGridField field5 = new ListGridField("amount"/* , "金额" */);
		Utils.formatPriceField(field5);

		itemListGrid.setFields( field2, field3, field4, field5);

		// 选中行事件
		itemListGrid.addCellClickHandler(new CellClickHandler() {

			public void onCellClick(CellClickEvent event) {
				// 先清空Form表单的值
				itemLdf.clearValues();

				// 将From定义为修改模式(连接服务器端时修改掉 ListGrid)
				itemLdf.editSelectedData(itemListGrid);

				Utils.makeAllNotSelectLG(quotationLG);
				
				//多选下拉菜单赋值
				Utils.setMultipleFormItemValue(certificateTypeItem);
				
				service.getPartInfoByPartNumber( event.getRecord().getAttribute("partNumber"),
						new AsyncCallback<PartInfoVO>() {

							@Override
							public void onFailure(Throwable arg0) {
								// TODO Auto-generated method stub
								SC.say("失败");
							}

							@Override
							public void onSuccess(PartInfoVO partVo) {
								// TODO Auto-generated method stub
								manufacturerItem.setValue(partVo.getManufacturerCodeId());//制造商
								keywordItem.setValue(partVo.getKeyword());//关键字
								ataItem.setValue(partVo.getAtaNumber());//ATA
								item8.setValue(partVo.getUnitOfMeasureCode());//单位
								partDescriptionItem.setValue(partVo.getDescription());//备件描述
							}
						});

			}

		});

		return itemListGrid;
	}

	/**
	 * 刷新ItemlistGrid
	 */
	private void refreshItemListGrid() {
		if (primaryKey == null) {
			primaryKey = "-1";
		}
		Criteria criteria = new Criteria();
		criteria.setAttribute("id", primaryKey);
		criteria.setAttribute("_r", String.valueOf(Math.random()));
		itemListGrid.fetchData(criteria);
	}

	/**
	 * 刷新ListGrid
	 * 
	 * @param grid
	 */
	public void refeshListGrid() {
		SalesRequisitionSheetBusiness bus = new SalesRequisitionSheetBusiness();
		bus.refeshListGrid();
	}

	/**
	 * 单击Grid 给Form 赋值
	 * 
	 * @param lgRecord
	 */
	private void setFormItemValues(ListGridRecord lgRecord) {
		ListGridRecord lgr = lgRecord;
		// 单击报价单明细选择
		item1.setValue(lgr.getAttribute("quotationPartNumber"));
		// Integer deliveryLeadTime=lgr.getAttributeAsDate("quotationDate");
		item2.setValue(lgr.getAttributeAsDate("partRequireDate"));
		item3.setValue(lgr.getAttribute("m_Priority"));
		item4.setValue(lgr.getAttribute("airIdentificationNumber"));
		item6.setValue(lgr.getAttribute("quantity"));
		item7.setValue(lgr.getAttribute("packageDescription"));
		item8.setValue(lgr.getAttribute("m_UnitOfMeasureCode"));
		item9.setValue(lgr.getAttribute("packagePrice"));
		item10.setValue(lgr.getAttribute("standardPackageQuantity"));
		item11.setValue(lgr.getAttribute("m_InternationalCurrencyCode"));
		// item12.setValue(lgr.getAttribute("m_ManufacturerCode"));
		item13.setValue(lgr.getAttribute("unitPriceAmount") + "");
		item14.setValue(lgr.getAttribute("paymentRequirement"));
		item15.setValue(lgr.getAttribute("amount"));
		item16.setValue(lgr.getAttribute("remark"));
		
		keywordItem.setValue(lgRecord.getAttribute("keyword"));// 关键字
		ataItem.setValue(lgRecord.getAttribute("ata"));// ATA章节号
		manufacturerItem.setValue(item1.getSelectedRecord()
				.getAttribute("m_ManufacturerCode.id"));
		partDescriptionItem.setValue(item1.getSelectedRecord().getAttribute(
				"remarkText"));

	}

	/***************************************************************************
	 * 计算金额
	 */
	private void alculationAmount() {
		float totalAmount = 0.00f;
		if (item6.getValue() != null && item13.getValue() != null) {

			totalAmount = Float.parseFloat(item6.getValue().toString())
					* Float.parseFloat(item13.getValue().toString());// 数量*单价
			if (item9.getValue() != null) {
				totalAmount += Float.parseFloat(item9.getValue().toString());// 特殊包装费用
			}

		}
		item15.setValue(totalAmount);

	}
}

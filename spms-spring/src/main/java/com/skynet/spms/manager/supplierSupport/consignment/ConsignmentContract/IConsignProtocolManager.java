package com.skynet.spms.manager.supplierSupport.consignment.ConsignmentContract;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.consignmentAgreementTemplate.ConsignmentAgreementTemplate;

/**
 * 
 * 描述：寄售协议操作接口
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
public interface IConsignProtocolManager {
	/**
	 * 
	 * 描述：添加一条记录
	 * 
	 * @param item
	 *            寄售协议实体
	 */
	public void addSheet(ConsignmentAgreementTemplate pact);

	/**
	 * 
	 * 描述：根据ID查询一条记录
	 * 
	 * @param id
	 *            UUID
	 * @return 返回查询到的实体，未查询到返回null
	 */
	public ConsignmentAgreementTemplate getById(String id);

	/**
	 * 
	 * 描述： 修改
	 * 
	 * @param newValues
	 *            待修改的值集合
	 * @param itemID
	 *            被修改的实体的uuid
	 * @return 返回修改后的实体
	 */
	public ConsignmentAgreementTemplate update(Map<String, Object> newValues,
			String itemID);

	/**
	 * 描述： 分页查询
	 * 
	 * @param values
	 *            查询条件集合,各个条件为and的关系
	 * @param startRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @return 返回查询结果集合
	 */
	public List<ConsignmentAgreementTemplate> doQuery(
			Map<String, Object> values, int startRow, int endRow);


	/**
	 * 描述： 分页查询
	 * 
	 * @param startRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @return 返回查询的结果集合
	 */
	public List<ConsignmentAgreementTemplate> getList(int startRow, int endRow);

	/**
	 * 描述： 删除一条记录，这里的删除实际上只是更新操作
	 * 
	 * @param itemID
	 *            被删除记录的UUID
	 */
	public void delete(String itemID);
}

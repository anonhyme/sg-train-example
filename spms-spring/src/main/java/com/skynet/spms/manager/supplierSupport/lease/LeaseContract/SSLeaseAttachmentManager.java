package com.skynet.spms.manager.supplierSupport.lease.LeaseContract;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.base.Attachment;

/**
 * 供应商租赁合同附件接口
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
public interface SSLeaseAttachmentManager {

	/**
	 * 添加一条记录
	 * 
	 * @param sheet
	 */
	public void addSheet(Attachment item);

	/**
	 * 根据ID查询一条记录
	 * 
	 * @param id
	 * @return
	 */
	public Attachment getById(String id);

	/**
	 * 
	 * 修改供应商租赁合同附件
	 * 
	 * @param 新数据对象
	 * @param 供应商租赁合同附件ID
	 * @return 供应商租赁合同附件对象
	 */
	public Attachment update(Map<String, Object> newValues, String itemID);

	/**
	 * 分页查询
	 * 
	 * @param 新数据对象
	 * @param 首页
	 * @param 尾页
	 * @return
	 */
	public List<Attachment> doQuery(Map<String, Object> values, int startRow,
			int endRow);

	/**
	 * 分页查询
	 * 
	 * @param 首页
	 * @param 尾页
	 * @return   供应商租赁合同附件集合对象
	 */
	public List<Attachment> getList(int startRow, int endRow);

	/**
	 * 删除一条记录，这里的删除实际上只是更新操作
	 * 
	 * @param itemID
	 */
	public void delete(String itemID);

}

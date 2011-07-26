/**
 * 
 */
package com.skynet.spms.manager.stockServiceBusiness.spareBoxBusiness.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.spareBoxBusiness.SpareBoxItemsManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.spareBoxBusiness.SpareBoxItems;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class SpareBoxItemsManagerImpl extends CommonManagerImpl<SpareBoxItems> implements SpareBoxItemsManager{

	@Override
	public List<SpareBoxItems> getSpareBoxItems(Map values, int startRow, int endRow) {
		Criteria criteria= getSession().createCriteria(SpareBoxItems.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, SpareBoxItems.class, null);
        
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}
}

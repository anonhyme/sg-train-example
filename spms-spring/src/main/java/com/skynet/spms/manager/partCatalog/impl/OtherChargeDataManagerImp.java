package com.skynet.spms.manager.partCatalog.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.OtherChargeDataManager;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.OtherCharge;

@Service
@Transactional
public class OtherChargeDataManagerImp extends CommonManagerImpl <OtherCharge> implements OtherChargeDataManager {

	@Override
	public List<OtherCharge> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(OtherCharge.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<OtherCharge> list = criteria.list();
		return list;
	}
   
}

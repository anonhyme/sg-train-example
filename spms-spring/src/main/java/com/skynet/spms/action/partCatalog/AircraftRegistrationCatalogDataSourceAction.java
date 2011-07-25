package com.skynet.spms.action.partCatalog;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.AircraftConfigCatalogManager;
import com.skynet.spms.persistence.entity.partCatalog.aircraftConfigCatalog.AircraftRegistration;
@Component
public class AircraftRegistrationCatalogDataSourceAction implements DataSourceAction<AircraftRegistration> {
	

	private Logger log=LoggerFactory.getLogger(AircraftRegistrationCatalogDataSourceAction.class);
	
	@Autowired
	AircraftConfigCatalogManager  aircraftConfigCatalogManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"aircraftConfig_dataSource"}; 
	}

	@Override
	public void insert(AircraftRegistration item) {
		log.info("===============AircraftRegistrationCatalogDataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("AircraftTailNumber : " + item.getAircraftTailNumber());
		log.info("Nationality : " + item.getM_CountryCode());
		log.info("Operator : " + item.getOperator());
		log.info("Owner : " + item.getOwner());
		log.info("FactoryDate : " + item.getFactoryDate());
		log.info("AircraftModelIdentifier : " + item.getM_AircraftModelIdentifier());
		log.info("AircraftRegistrationNumber : " + item.getAircraftRegistrationNumber());
		log.info("RegistrationDate : " + item.getRegistrationDate());
		aircraftConfigCatalogManager.insertEntity(item);
	}

	@Override
	public AircraftRegistration update(Map<String, Object> newValues, String itemID) {
		log.info("===============AircraftRegistrationCatalogDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		AircraftRegistration pad = aircraftConfigCatalogManager.updateEntity(newValues, itemID, AircraftRegistration.class);
		
		return pad;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============AircraftRegistrationCatalogDataSourceAction.delete()");
		log.info("AircraftConfigurationId ：" + itemID);
		aircraftConfigCatalogManager.deleteEntity(itemID, AircraftRegistration.class);
	}

	@Override
	public List<AircraftRegistration> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============AircraftRegistrationCatalogDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List criteria = (List)values.remove("criteria");
		List<AircraftRegistration> list = null;
		if(criteria == null){
			list = aircraftConfigCatalogManager.queryByProps(values);
		}else{
			list = aircraftConfigCatalogManager.queryByFilter(criteria);
		}
	
		return list;
	}

	@Override
	public List<AircraftRegistration> getList(int startRow, int endRow) {
		log.info("===============AircraftRegistrationCatalogDataSourceAction.getList()");
		List<AircraftRegistration> list = aircraftConfigCatalogManager.list(startRow, endRow, AircraftRegistration.class);
		
		return list;
	}
	
	
}

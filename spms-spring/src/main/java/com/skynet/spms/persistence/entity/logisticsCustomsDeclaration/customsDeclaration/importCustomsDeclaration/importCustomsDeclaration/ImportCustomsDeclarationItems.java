package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.baseCustomsDeclaration.baseCustomsDeclarationPartItem.BaseCustomsPartItem;

/**
 * @author FANYX
 * @version 1.0  进口报关备件项
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_IMPORT_CUS_DEC_ITEMS")
public class ImportCustomsDeclarationItems extends BaseCustomsPartItem {
	/**
	 * 原产地
	 */
	private String countryOfOrigin;
	
	public ImportCustomsDeclarationItems() {

	}
	public void finalize() throws Throwable {
		super.finalize();
	}
	
	@Column(name = "COUNTRY_OF_ORIGIN")
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	
}

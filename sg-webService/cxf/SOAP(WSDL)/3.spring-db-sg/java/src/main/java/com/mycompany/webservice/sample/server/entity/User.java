package com.mycompany.webservice.sample.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cxf_user")
//element type & complexType name
@XmlType(name = "User" , propOrder = {"id", "name", "email" , "lastUpdateDate"})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    //element name for show, make first char Higher
//    @XmlElement(name = "Id",required = true)
    private String id;// ID
//    @XmlElement(name="Name", required = true)
    private String name;//name
//    @XmlElement(name = "Email", required = true)
    private String email;//email

    private Date lastUpdateDate;//create date

    @Id
	@Column(length = 36, nullable = true)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  //生成器名称，uuid生成类
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}

package com.sumo.phoneValidation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class country {

    @Id
    @GeneratedValue(generator = "uuid2")
    private Integer id ;
    private String name ;
    private String code ;
    private String CRegex;



    @Override
    public String toString() {
        return "country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", CRegex='" + CRegex + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public country() {
    }

    public country(String name, String code, String CRegex) {
        this.name = name;
        this.code = code;
        this.CRegex = CRegex;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCRegex() {
        return CRegex;
    }

    public void setCRegex(String CRegex) {
        this.CRegex = CRegex;
    }

    public void setName(String name) {
        this.name = name;
    }

}

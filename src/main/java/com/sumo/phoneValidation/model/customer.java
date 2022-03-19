package com.sumo.phoneValidation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sumo.phoneValidation.Spring;
import com.sumo.phoneValidation.countryService;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class customer {

    @Id
    @GeneratedValue(generator = "uuid2")
    private Integer id ;
    private String name;
    private  String phone ;
    private String state;
    private String country ;
    private String code ;

    public String getState() {return state;}


    public void setState(String state)
    {
        if(state.equals(""))
        {
            countryService cs = Spring.bean(countryService.class);
            this.state = cs.GetState(phone);
        }
        else
        {
            this.state = state;
        }
    }

    public String getCountry() {return country;}


    public void setCountry(String country) {
        if(country.equals("") )
        {
            countryService cs = Spring.bean(countryService.class);
            this.country = cs.GetCountry(phone);
        }
        else
        {
            this.country = country;
        }
    }

    public String getCode() {return code;}


    public void setCode(String code) {
        if(code.equals("") )
        {
            countryService cs = Spring.bean(countryService.class);
            this.code = cs.GetCode(phone);
        }
        else
        {
            this.code = code;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        setCode("");
        setCountry("");
        setState("");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

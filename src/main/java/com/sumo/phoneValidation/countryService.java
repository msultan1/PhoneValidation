package com.sumo.phoneValidation;
import com.sumo.phoneValidation.model.country;
import com.sumo.phoneValidation.model.customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

@Service
public class countryService {

    private countryRepo repo ;

    private customerRepo csRepo ;

    @Autowired
    public void setCsRepo(customerRepo csRepo){
        this.csRepo = csRepo;
    }

    @Autowired
    public void setRepo(countryRepo repo) {
        this.repo = repo;
    }

    public String GetCode (String phone)
    {
        String Phone_code = phone.split("\\)")[0].split("\\(")[1];
        return Phone_code;
    }

    public String  GetState (String phone)
    {
        List s = repo.findAll();
        String state = "" ;
        String Phone_code = phone.split("\\)")[0].split("\\(")[1];
        for (int i=0;i<s.size();i++) {
            country temp = (country) s.get(i);
            if (temp.getCode().equals("+"+Phone_code)) {
                Pattern pattern = Pattern.compile(temp.getCRegex());
                Matcher matcher = pattern.matcher(phone);
                boolean matchFound = matcher.find();
                if (matchFound){
                    state =  "Valid";
                }
                else{
                    state = "InValid";
                }
                break;
            }
        }
        return state;
    }

    public String GetCountry (String phone)
    {
        List s = repo.findAll();
        String country = "" ;
        String Phone_code = phone.split("\\)")[0].split("\\(")[1];
        for (int i=0;i<s.size();i++) {
            country temp = (country) s.get(i);
            if (temp.getCode().equals("+"+Phone_code)) {
                country =  temp.getName();
                break;
            }
        }
        return country;
    }

    public void compute ()
    {
        List ListCS = csRepo.findAll();
        for (int i=0;i<ListCS.size();i++)
        {
           customer temp = (customer) ListCS.get(i);
           temp.setCode(GetCode(temp.getPhone()));
           temp.setCountry(GetCountry(temp.getPhone()));
           temp.setState(GetState(temp.getPhone()));
           ListCS.set(i,temp);
        }
        csRepo.saveAllAndFlush(ListCS);
    }

}

package com.ai.testredis.entity;

import java.io.Serializable;

public class Organization implements Serializable {

   static final long serialVersionUID = 1L;
   private int id ;
   private String orgCode;
   private String orgName;
   private String provinceCode ;

   public Organization(){

   }

   public Organization(int id,String orgCode,String orgName,String provinceCode){
       this.id = id ;
       this.orgCode = orgCode;
       this.orgName = orgName;
       this.provinceCode = provinceCode;
   }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

}

package com.news.sph.home.dto;

import com.news.sph.common.dto.BaseDTO;

/**
 * Created by lenovo on 2016/5/26.
 */
public class TranDTO extends BaseDTO {
    private String bat_code;
    private String sna_code;
    public String getBat_code() {
        return bat_code;
    }

    public void setBat_code(String bat_code) {
        this.bat_code = bat_code;
    }

    public String getSna_code() {
        return sna_code;
    }

    public void setSna_code(String sna_code) {
        this.sna_code = sna_code;
    }




}

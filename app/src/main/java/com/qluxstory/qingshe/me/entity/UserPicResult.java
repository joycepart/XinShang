package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/25.
 */
public class UserPicResult extends BaseEntity {
    List<UserPicEntity> data;
    public List<UserPicEntity> getData() {
        return data;
    }

    public void setData(List<UserPicEntity> data) {
        this.data = data;
    }
}

package com.news.sph.information.fragment;

import android.view.View;

import com.news.ptrrecyclerview.BaseRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.common.base.BaseListFragment;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.information.adapter.InformationAdapter;
import com.news.sph.information.entity.InformationEntity;
import com.news.sph.information.entity.InformationResult;
import com.news.sph.utils.LogUtils;

import java.io.Serializable;
import java.util.List;

/*
消息的fragment
*/

public class InformationFragment extends BaseListFragment<InformationEntity> {

    private String mNewsBigTitle;
    private String mNewsHtmlUrl;


    @Override
    public void initView(View view) {
//        setTitleText("系统通知");
        super.initView(view);

    }

    @Override
    public BaseRecyclerAdapter<InformationEntity> createAdapter() {
        return new InformationAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "InformationFragment";
    }

    @Override
    public List<InformationEntity> readList(Serializable seri) {
        return ((InformationResult)seri).getData();
    }

    @Override
    protected void sendRequestData() {
        BaseDTO gdto=new BaseDTO();
        gdto.setPageSize(PAGE_SIZE);
        gdto.setPageIndex(mCurrentPage);
        CommonApiClient.getNews(getActivity(), gdto, new CallBack<InformationResult>() {
            @Override
            public void onSuccess(InformationResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("获取新闻成功");
                    requestDataSuccess(result);//获取到数据后调用该语句，进行数据缓存
                    setDataResult(result.getData());//设置数据
                }

            }
        });


    }

    public boolean autoRefreshIn(){
        return true;
    }

    @Override
    public void initData() {
        sendRequestData();
    }

    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        super.onItemClick(itemView, itemBean, position);

    }
}

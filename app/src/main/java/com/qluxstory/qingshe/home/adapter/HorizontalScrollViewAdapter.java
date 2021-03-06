package com.qluxstory.qingshe.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.MainActivity;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.SimplePage;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.UIHelper;
import com.qluxstory.qingshe.home.entity.HomeRecommendEntity;
import com.qluxstory.qingshe.issue.entity.IssueProduct;
import com.qluxstory.qingshe.me.MeUiGoto;

import java.util.ArrayList;
import java.util.List;

public class HorizontalScrollViewAdapter
{
	IssueProduct issueProduct;
	private Context mContext;
	private LayoutInflater mInflater;
	private List<HomeRecommendEntity> mDatas=new ArrayList<>();

	public HorizontalScrollViewAdapter(Context context)
	{
		issueProduct = AppContext.getInstance().getIssueProduct();
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
	}

	public void append(List<HomeRecommendEntity> mDatas){
		this.mDatas=mDatas;
	}

	public int getCount()
	{
		return mDatas.size();
	}

	public HomeRecommendEntity getItem(int position)
	{
		return mDatas.get(position);
	}

	public long getItemId(int position)
	{
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder = null;
		viewHolder = new ViewHolder();
		if(getCount()==(position+1)){
			convertView = mInflater.inflate(
					R.layout.item_lin_horizontal, parent, false);
			viewHolder.mItemLin = (LinearLayout) convertView
					.findViewById(R.id.item_lin);
			viewHolder.mItemLin.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(mContext, MainActivity.class);
					intent.putExtra("tag",2);
					mContext.startActivity(intent);
				}
			});
			return convertView;

		}
		if (convertView == null)
		{
			convertView = mInflater.inflate(
					R.layout.activity_index_gallery_item, parent, false);
			viewHolder.mItemRel = (LinearLayout) convertView
					.findViewById(R.id.gallery_item_rel);
			viewHolder.mImg = (ImageView) convertView
					.findViewById(R.id.id_img);
			viewHolder.mTitle = (TextView) convertView
					.findViewById(R.id.id_title);
			viewHolder.mNum = (TextView) convertView
					.findViewById(R.id.id_num);
			viewHolder.mTerm = (TextView) convertView
					.findViewById(R.id.id_tirm);


			convertView.setTag(viewHolder);
		} else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}

		ImageLoaderUtils.displayImage(AppConfig.BASE_URL+getItem(position).getPic_url(),viewHolder.mImg);
		viewHolder.mTitle.setText(getItem(position).getSna_title());
		viewHolder.mNum.setText("¥ "+getItem(position).getSna_total_count());
		viewHolder.mTerm.setText("第"+getItem(position).getSna_term()+"期");
		viewHolder.mItemRel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean bool= AppContext.get("isLogin",false);
				if(bool){
					Bundle b = new Bundle();
					String bat = getItem(position).getBat_code();
					String sna = getItem(position).getSna_code();
					b.putString("bat",bat);
					b.putString("sna",sna);
					LogUtils.e("mBat----",""+bat);
					LogUtils.e("mSna----",""+sna);
					LogUtils.e("Pic_url----",""+getItem(position).getPic_url());
					issueProduct.setmPicUrl(getItem(position).getPic_url());
					UIHelper.showFragment(mContext, SimplePage.PRODUCT_DETAILS,b);//夺宝商品详情
				}else {
					MeUiGoto.login((Activity) mContext);//登录
				}

			}
		});
		return convertView;
	}

	static class ViewHolder
	{
		ImageView mImg;
		TextView mTitle;
		TextView mTerm;
		TextView mNum;
		LinearLayout mItemRel,mItemLin;
	}

}

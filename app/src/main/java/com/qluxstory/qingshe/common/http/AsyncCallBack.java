package com.qluxstory.qingshe.common.http;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.common.entity.BaseEntity;
import com.qluxstory.qingshe.common.eventbus.ErrorEvent;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TDevice;

import java.io.IOException;

import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AsyncCallBack<T> implements Callback {
	CallBack<T> callback;
	Class<T> clazz;
	Context context;
	public static final Gson gson = new Gson();
	private Object tag;

	public AsyncCallBack(Activity act, CallBack<T> callback, Class<T> clazz) {
		this.context = act;
		this.callback = callback;
		this.clazz = clazz;
		this.tag = act;
	}
	public AsyncCallBack(Fragment fragment, CallBack<T> callback, Class<T> clazz) {
		this.context = fragment.getActivity();
		this.callback = callback;
		this.clazz = clazz;
		this.tag = fragment;
	}

	@Override
	public void onFailure(Call call, IOException e) {
		LogUtils.i("request failed "+e+ "   e.msg:"+e.getMessage());
		if(!TDevice.hasInternet(context)){
			EventBus.getDefault().post(
					new ErrorEvent(AppConfig.ERROR_NONET,
							AppConfig.ERROR_NONET_MSG, tag));
		}else {
			String msg = e.getMessage();
			if (!"Canceled".equals(msg)) {
				EventBus.getDefault().post(
						new ErrorEvent(AppConfig.ERROR_REQ,
								AppConfig.ERROR_REQ_MSG, tag));
			}
		}
	}

	@Override
	public void onResponse(Call call, Response response) throws IOException {
		if (response.isSuccessful()) {

			String reader = response.body().string();
			LogUtils.e("reader--------",reader);
			int startIndex=reader.indexOf("{",reader.indexOf("{")+1);
			int endIndex=reader.lastIndexOf("]");
			reader=reader.substring(startIndex,endIndex);

			LogUtils.e("response success json-->" + reader);
			try {
				T t = gson.fromJson(reader.trim(), clazz);
				callback.sendMsg(CallBack.SUCCESS, t);
				BaseEntity entity=(BaseEntity)t;
				EventBus.getDefault().post(
						new ErrorEvent(entity.getStatus(),
								entity.getMsg(), tag));
			}catch (Exception e){
				callback.sendMsg(CallBack.FAIL, (T) AppConfig.ERROR_PARSER_MSG);
				EventBus.getDefault().post(
						new ErrorEvent(AppConfig.ERROR_PARSER,
								AppConfig.ERROR_PARSER_MSG, tag));
				LogUtils.i("response json parse error "+e);
				e.printStackTrace();
			}
		} else {
			callback.sendMsg(CallBack.FAIL, (T) AppConfig.ERROR_IO_MSG);
			EventBus.getDefault().post(
					new ErrorEvent(AppConfig.ERROR_IO,
							AppConfig.ERROR_IO_MSG, tag));
			LogUtils.i("response is not Successful "+response);
		}
	}

	public Object getTag() {
		return tag;
	}

	public void setTag(Object tag) {
		this.tag = tag;
	}


}
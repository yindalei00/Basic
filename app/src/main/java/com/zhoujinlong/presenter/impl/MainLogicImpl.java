package com.zhoujinlong.presenter.impl;

import com.android.core.model.Canceller;
import com.zhoujinlong.model.bean.Classify;
import com.zhoujinlong.model.http.Factory;
import com.zhoujinlong.presenter.MainLogic;
import com.zhoujinlong.presenter.base.LoadSuccessLogicImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 12:05
 * @GitHub: https://github.com/meikoz
 */
public class MainLogicImpl extends LoadSuccessLogicImpl implements MainLogic {
    @Override
    public Canceller onLoadRemoteData(final boolean isMore, int page) {
        final Call<Classify> c = Factory.provideHttpService().getImageClassify(page);
        c.enqueue(new Callback<Classify>() {
            @Override
            public void onResponse(Call<Classify> call, Response<Classify> response) {
                onLoadListSuccessHandle(response, isMore);
            }

            @Override
            public void onFailure(Call<Classify> call, Throwable t) {
                onLoadFail("wangluocuwu");
            }
        });
        return new Canceller() {
            @Override
            public void Cancel() {
                c.cancel();
            }
        };
    }
}

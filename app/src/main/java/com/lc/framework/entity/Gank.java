package com.lc.framework.entity;

import com.lc.core.entity.BaseListEntity;
import com.lc.core.entity.HttpResult;
import com.lc.core.utils.CollectionUtils;
import com.lc.core.utils.helper.RxSchedulers;
import com.lc.framework.api.Api;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

/**
 * Created by LiangCheng on 2017/8/22.
 */

public class Gank extends BaseListEntity {

    public String desc;


    public String url;

    public String imageUrl;

    public String who;

    public List<String> images;

    @Override
    public Observable<HttpResult<List<Gank>>> getPage(int page) {
        return Api.getInstance().service.getGankData(param.get("gank"), page)
                .zipWith(Api.getInstance().service.getGankData("福利", page),
                        (BiFunction<HttpResult<List<Gank>>, HttpResult<List<Gank>>, HttpResult<List<Gank>>>)
                                (listHttpResult, listHttpResult2) -> {
                                    HttpResult zipItems = new HttpResult();
                                    Gank zipItem;
                                    List<Gank> zipResults = new ArrayList<>();
                                    for (int i = 0; i < listHttpResult2.results.size(); i++) {
                                        zipItem = new Gank();
                                        Gank item = listHttpResult2.results.get(i);
                                        Gank productInfo = listHttpResult.results.get(i);
                                        if (CollectionUtils.isEmpty(productInfo.images)) {
                                            zipItem.imageUrl = item.url;
                                        } else {
                                            zipItem.imageUrl = productInfo.images.get(0);
                                        }
                                        zipItem.url = productInfo.url;
                                        zipItem.desc = productInfo.desc;
                                        zipItem.who = productInfo.who;
                                    }
                                    zipItems.results = zipResults;
                                    return zipItems;
                                })
                .compose(RxSchedulers.io_main());
    }


}

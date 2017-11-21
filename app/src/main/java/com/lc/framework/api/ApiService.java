package com.lc.framework.api;

import com.lc.core.entity.HttpResult;
import com.lc.framework.entity.Gank;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by LiangCheng on 2017/8/22.
 */

public interface ApiService {

    @GET("data/{gank}/10/{page}")
    Observable<HttpResult<List<Gank>>> getGankData(@Path("gank") String gank,
                                                   @Path("page") int page);
}

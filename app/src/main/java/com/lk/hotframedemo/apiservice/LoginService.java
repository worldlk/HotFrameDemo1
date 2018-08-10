package com.lk.hotframedemo.apiservice;

import com.lk.hotframedemo.bean.Login;
import com.lk.hotframedemo.constant.UrlConstants;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by LiuKai on 2018/8/10
 */
public interface LoginService {
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @Headers({HEADER_API_VERSION})
    @GET("/users")
    Observable<List<Login>> getLogin(@Query("since") int lastIdQueried);
}

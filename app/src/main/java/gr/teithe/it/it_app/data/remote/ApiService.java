/*
 * Copyright (C) 2018-2020 Raf
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package gr.teithe.it.it_app.data.remote;

import gr.teithe.it.it_app.data.model.Announcement;
import gr.teithe.it.it_app.data.model.Category;
import gr.teithe.it.it_app.data.model.File;
import gr.teithe.it.it_app.data.model.NotificationsResponse;
import gr.teithe.it.it_app.data.model.TokenResponse;
import gr.teithe.it.it_app.data.model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService
{
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST
    Call<TokenResponse> refreshToken(@Url String url,
                                     @Field("client_id") String clientId,
                                     @Field("client_secret") String clientSecret,
                                     @Field("grant_type") String grantType,
                                     @Field("code") String code);

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST
    Observable<TokenResponse> authenticate(@Url String url,
                                           @Field("client_id") String clientId,
                                           @Field("client_secret") String clientSecret,
                                           @Field("grant_type") String grantType,
                                           @Field("code") String code);

    @GET("{path}?sort=-date")
    Call<List<Announcement>> getPagingAnnouncements(@Path(value = "path", encoded = true) String path,
                                                    @Query("pageSize") int pageSize,
                                                    @Query("page") long page);

    @GET("{path}")
    Call<List<Category>> getSyncCategories(@Path(value = "path", encoded = true) String path);

    @GET("{path}?sort=-date")
    Observable<List<Announcement>> getAnnouncements(@Path(value = "path", encoded = true) String path);

    @GET("announcements/{id}?fields=title,text,publisher,attachments,date")
    Observable<Announcement> getAnnouncement(@Path("id") String id);

    @GET("{path}")
    Observable<List<Category>> getCategories(@Path(value = "path", encoded = true) String path);

    @GET("categories/isRegistered")
    Observable<List<Category>> getRegisteredCategories();

    @GET("profile")
    Observable<User> getProfile();

    @GET("notifications")
    Observable<NotificationsResponse> getNotifications();

    @FormUrlEncoded
    @POST("user/chmail")
    Observable<Response<ResponseBody>> postChangeEmail(@Field("newMail") String newMail);

    @FormUrlEncoded
    @POST("user/chpw")
    Observable<Response<ResponseBody>> postChangePassword(@Field("oldPassword") String oldPassword,
                                                          @Field("newPassword") String newPassword);

    @POST("notifications")
    Observable<Response<ResponseBody>> postNotifications();

    @PATCH("profile")
    Observable<Response<ResponseBody>> postProfile(@Body FormBody params);

    @GET("files/{id}")
    Observable<File> getFile(@Path("id") String id);

    @PUT("categories/register")
    Observable<Response<ResponseBody>> putCategories(@Body RequestBody body);
}

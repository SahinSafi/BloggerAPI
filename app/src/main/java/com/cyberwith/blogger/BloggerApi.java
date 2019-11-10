package com.cyberwith.blogger;

import com.cyberwith.blogger.models.PostList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class BloggerApi {

    private static final String KEY = "AIzaSyCRMTaYEGIR17whl3WBG7qDkJFTg1WACQw";
    private static final String URL = "https://www.googleapis.com/blogger/v3/blogs/7447077068242782774/posts/";

    public static PostService postService = null;

    public static PostService getService(){
        if (postService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postService = retrofit.create(PostService.class);
        }
        return postService;
    }

    public interface PostService{
        @GET("?key="+ KEY)
        Call<PostList> getPostList();
    }
}

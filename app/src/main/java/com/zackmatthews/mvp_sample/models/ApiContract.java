package com.zackmatthews.mvp_sample.models;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by zmatthews on 3/19/18.
 */

public class ApiContract {
    private RequestQueue queue;

    public RequestQueue getQueue(Context context) {
        if(queue == null) {
            queue = Volley.newRequestQueue(context);
            queue.getCache().clear();
            queue.start();
        }

        return queue;
    }
}

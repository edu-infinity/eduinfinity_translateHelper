package com.eduinfinity.dimu.translatehelper.http;

/**
 * Created by Dimu on 10/23/14.
 */

import android.util.Log;

import com.loopj.android.http.*;

import org.apache.http.auth.AuthScope;

public class TXRestClient {
    private static final String BASE_URL = "https://www.transifex.com/api/2/project/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {

        client.get(getAbsoluteUrl(url), params, responseHandler);

    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        client.setBasicAuth("relaxgo", "126bhgn333");
        return BASE_URL + relativeUrl;
    }
}

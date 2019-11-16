package com.quiz.diggingquiz.myquiz.Utils.server;

import android.app.ProgressDialog;
import android.content.Context;

import android.util.Log;
import com.android.volley.*;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.diggingquiz.myquiz.Utils.Interface.ApiCallbackListener;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





public class ApiCallingMethods {
    /**
     * This method is called when we need to hit service in POST method.
     *
     * @param listKey
     * @param listValue
     * @param url
     * @param context
     * @param listener

     */




    public static void requestForPost(final List<String> listKey, final List<String> listValue, String url, Context context, final ApiCallbackListener listener, final String flag)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                   // progressDialog.dismiss();
                    if (listener != null) {
                        listener.onResultCallback(response,flag);
                    }
                } catch (Exception f) {
                    f.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // As of f605da3 the following should work
                        NetworkResponse response = error.networkResponse;
                        if (error instanceof ServerError && response != null) {
                            try {
                                String res = new String(response.data,
                                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                // Now you can use any deserializer to make sense of data
                                JSONObject obj = new JSONObject(res);
                            } catch (UnsupportedEncodingException e1) {
                                // Couldn't properly decode data to string
                                e1.printStackTrace();
                            } catch (JSONException e2) {
                                // returned data is not JSONObject?
                                e2.printStackTrace();
                            }
                        }
                        try {
                            //progressDialog.dismiss();
                            if (listener != null)
                                listener.onErrorCallback(error);
                        } catch (Exception f) {
                            f.printStackTrace();
                        }
                    }
                }

        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();

                for (int i = 0; i < listKey.size(); i++) {
                    map.put(listKey.get(i), listValue.get(i));
                }
                return map;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        Log.e("PostRequest",""+stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    /**
     * This method is called when we want to hit web service in GET method
     *
     * @param url
     * @param context
     * @param listener
     * @param progressDialog
     */
    public static void requestForGet(String url, Context context, final ApiCallbackListener listener, final ProgressDialog progressDialog, final String flag) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                if (listener != null) {
                    listener.onResultCallback(response,flag);
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        if (listener != null)
                            listener.onErrorCallback(error);
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}

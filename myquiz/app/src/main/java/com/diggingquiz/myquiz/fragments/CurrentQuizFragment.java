package com.diggingquiz.myquiz.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.*;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.diggingquiz.myquiz.R;
import com.diggingquiz.myquiz.Utils.Interface.ApiCallbackListener;
import com.diggingquiz.myquiz.Utils.preferences.PrefManager;
import com.quiz.diggingquiz.myquiz.Utils.server.ApiCallingMethods;
import com.quiz.diggingquiz.myquiz.Utils.server.Config;
import com.diggingquiz.myquiz.activities.UserLoginActivity;
import com.diggingquiz.myquiz.adapter.CurrentExamQuizeAdapter;
import com.diggingquiz.myquiz.base.BaseFragment;
import com.diggingquiz.myquiz.beans.QuizeListData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Tanuj yadav on 18/03/2018.
 */

public class CurrentQuizFragment extends BaseFragment implements ApiCallbackListener {

    private Context context;
    RecyclerView rv_currentQuiz;

    final ViewGroup nullParent = null;
    RecyclerView.Adapter adapter;
    ArrayList<Date> satSunArraylist;
    ArrayList<QuizeListData> quizeListDataArrayList;
    public static ArrayList<QuizeListData> quizeFilterArrayList;
    ArrayList<QuizeListData> deletedArrayList;
    int QuizeNo = 55;

    int count = 0;
PrefManager prefManager;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aboutus, container, false);
        /*getActivity().setTitle("Home");*/

        initViews(view);


        context = getActivity();
        satSunArraylist = new ArrayList<>();
        quizeListDataArrayList = new ArrayList<>();
        quizeFilterArrayList = new ArrayList<>();
        deletedArrayList=new ArrayList<>();
        prefManager=new PrefManager(getActivity());
        getDateRange();
        currentQuizeApi();
//        String sjnsd= UserLoginActivity.userProfileData.getAcc_Name();

      // Toast.makeText(getActivity(),sjnsd,Toast.LENGTH_LONG).show();
        return view;
    }
    public void getDateRange() {
        Date begining, end;

        {
            Calendar calendar = getCalendarForNow();
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            setTimeToBeginningOfDay(calendar);
            begining = calendar.getTime();
        }

        {
            Calendar calendar = getCalendarForNow();
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            setTimeToEndofDay(calendar);
            end = calendar.getTime();
        }

        //weekEndCount(begining,end);
        // weekEndCount(begining, end);
        Date c = Calendar.getInstance().getTime();


        SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        //weekEndCount(begining,end);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
        Date d1=null,d2=null;
        try {
             d1= df1.parse(prefManager.getActivatedate());
             d2=df1.parse(prefManager.getExpireDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.quizeListDataArrayList= weekEndCount(d1,d2,Integer.valueOf(prefManager.getNoOfQuiz()));
        if(!prefManager.getisQuestionSave().equalsIgnoreCase(getResources().getString(R.string.allreadycreated))){
            apiHitAllQuiz(quizeListDataArrayList);
        }


        Date currentdate = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //FilterData(simpleDateFormat.format(currentdate),simpleDateFormat.format(currentdate));

    }
    public void setActivateddate() throws ParseException {
        String activatedDate=prefManager.getActivatedate();
        String expiredate=prefManager.getExpireDate();
        DateFormat df1 = new SimpleDateFormat("dd/MMM/yyyy");
        Date d1 = df1 .parse(activatedDate);
    }

    private void initViews(View v) {

        rv_currentQuiz = v.findViewById(R.id.rv_currentQuiz);
        LinearLayoutManager layoutManager5 = new LinearLayoutManager(context);
        layoutManager5.setOrientation(LinearLayoutManager.VERTICAL);
        rv_currentQuiz.setLayoutManager(layoutManager5);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


        }
    }






    public void FilterData(String dateString1, String dateString2){


        quizeFilterArrayList.clear();
        quizeFilterArrayList = (ArrayList<QuizeListData>) quizeListDataArrayList.clone();
        DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat df2 = new SimpleDateFormat("dd/MMM/yyyy");

        Date date1 = null;
        Date date2 = null;
        Date filterDate = null;
        for(int i=0;i<quizeFilterArrayList.size();i++){
            try {
                date1 = df1 .parse(dateString1);
                date2 = df1 .parse(dateString2);
                filterDate=df2 .parse(quizeFilterArrayList.get(i).getDate());

                if(CheckDate(date1,date2,filterDate)){
                    // deletedArrayList.add(quotationFilterArrayList.get(i));
                    //sho
                    //showToast("Sucess");
                }else{
                    deletedArrayList.add(quizeFilterArrayList.get(i));
                    //quotationFilterArrayList.add(quotationFilterArrayList.get(i));
                    //showToast("False");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }
        for (int i = 0; i < deletedArrayList.size(); i++) {
            for (int j = 0; j < quizeFilterArrayList.size(); j++) {
                if (deletedArrayList.get(i).getDate().equalsIgnoreCase(quizeFilterArrayList.get(j).getDate())) {
                    quizeFilterArrayList.remove(j);
                }
            }
        }

        //  quotationFilterArrayList = (ArrayList<QuotationData>) deletedArrayList.clone();
        LinearLayoutManager layoutManager5 = new LinearLayoutManager(context);
        layoutManager5.setOrientation(LinearLayoutManager.VERTICAL);
        rv_currentQuiz.setLayoutManager(layoutManager5);
        Collections.sort(quizeFilterArrayList, new Comparator<QuizeListData>(){
            public int compare (QuizeListData m1, QuizeListData m2){
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
                Date d1=null;
                Date d2=null;
                try {
                    d1 = sdf.parse(m1.getDate());
                    d2 = sdf.parse(m2.getDate());
                    if (m1.getDate() == null || m1.getDate() == null)
                        return 0;

                } catch (ParseException ex) {
                    Log.v("Exception", ex.getLocalizedMessage());
                }
                return d1.compareTo(d2);
            }
        });
      /*  adapter = new CurrentExamQuizeAdapter(getActivity(), quizeFilterArrayList,0);
        rv_currentQuiz.setAdapter(adapter);
        adapter.notifyDataSetChanged();*/

    }
    public boolean CheckDate(Date min,Date  max,Date FilterDate){
        // assume these are set to something
        // the date in question
        Calendar cal = Calendar.getInstance();
        cal.setTime(min);
        cal.add(Calendar.DATE, -1);
        Date oneBeforeDays = cal.getTime();
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(max);
        cal2.add(Calendar.DATE, 1);
        Date NextDays = cal2.getTime();

        return FilterDate.after(oneBeforeDays) && FilterDate.before(NextDays);
    }

    @Override
    public void onResume() {
        super.onResume();
       //    getDateRange();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
    public void apiHitAllQuiz(ArrayList<QuizeListData> quizelist)  {
        final Map<String, Object> params = new HashMap<String, Object>();
        Gson gson = new Gson();
        String quizData = gson.toJson(quizelist);


        Log.e("quizdsts",""+quizData);
        //params.put("QuizeList", quizelist);
        String url="";



        JsonElement element = gson.toJsonTree(quizelist, new TypeToken<List<QuizeListData>>() {}.getType());

        if (! element.isJsonArray()) {
        // fail appropriately

        }

        JsonArray jsonArray = element.getAsJsonArray();

        //JSONArray jsonArray1=createArray(quizelist);
        Type baseType = new TypeToken<ArrayList<QuizeListData>>() {}.getType();


        String jsonString = gson.toJson(quizelist, baseType);
        JSONArray myjsonarray=null;
        try {
             myjsonarray = new JSONArray(jsonString);
            params.put("QuizeList", myjsonarray);
            params.put("UserId", prefManager.getUserName());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = null;
        request = new JsonObjectRequest(Config.allquizSubmit, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Responce",""+response);
                        prefManager.setisQuestionSave(getResources().getString(R.string.allreadycreated));
                        //TODO verificar o status code da resposta apenas deverá registar login caso seja 200
                        //verifica
                        Log.d("response",response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String body;
                //get response body and parse with appropriate encoding
                if(error.networkResponse.data!=null) {
                    String statusCode = String.valueOf(error.networkResponse.statusCode);
                    try {
                        body = new String(error.networkResponse.data,"UTF-8");
                        JSONObject jsonObj = new JSONObject(body);
                        Log.d("body",String.valueOf(jsonObj.get("message")));
                        //sh(String.valueOf(jsonObj.get("message")));
                    } catch (UnsupportedEncodingException e) {
                        //showToast("You need to connect to the internet!");
                    } catch (JSONException e) {
                        Log.d("json:","problems decoding jsonObj");
                    }
                }
                //do stuff with the body...
            }


        });

        request.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

       // queue.add(request);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);

    }


    private void currentQuizeApi() {

        showProgressDialog("fetch Quiz...");
        List<String> listKey = new ArrayList<>();
        List<String> listValue = new ArrayList<>();


        listKey.add("UserId");


        listValue.add(prefManager.getUserName());


        ApiCallingMethods.requestForPost(listKey, listValue, Config.CurrentQuiz, getActivity(), this, "CurentQuiz");

    }


    @Override
    public void onResultCallback(String response, String flag) {
        Log.e("Responce",response);
        dismissDialog();
        try {
        if(flag!=null && flag.equalsIgnoreCase("CurentQuiz")){
            JSONObject objMain = null;

                objMain = new JSONObject(response);

            String merssge = objMain.getString("message");

           // Toast.makeText(getActivity(), objMain.getString("message"), Toast.LENGTH_SHORT).show();
            JSONArray jsoncurrentquiz = new JSONArray(objMain.getString("CurrentQuizList"));
            for (int i = 0; i < jsoncurrentquiz.length(); i++) {
                JSONObject obj = jsoncurrentquiz.getJSONObject(i);
                QuizeListData quizeListData=new QuizeListData();
                quizeListData.setDate(obj.getString("date"));
                quizeListData.setExpireTime(Long.valueOf(obj.getString("expireTime")));
                quizeListData.setQuizeDateTime(obj.getString("quizeDateTime"));
                quizeListData.setQuizeName(obj.getString("quizeName"));
                quizeListData.setWinningamount(Double.valueOf(obj.getString("winningamount")));
                quizeListData.setId(Integer.valueOf(obj.getString("QuizId")));
                quizeFilterArrayList.add(quizeListData);
            }
            adapter = new CurrentExamQuizeAdapter(getActivity(), quizeFilterArrayList,0);
            rv_currentQuiz.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorCallback(VolleyError error) {
        dismissDialog();
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            showAlertDialog("Ah!", "It seems that your internet connection is too slow or not found.");
        } else if (error instanceof AuthFailureError) {
            showAlertDialog("Ah!", "Something went wrong, please try again later");
        } else if (error instanceof ServerError) {
            showAlertDialog("Ah!", "Something went wrong, please try again later");
        } else if (error instanceof NetworkError) {
            showAlertDialog("Ah!", "Something went wrong, please try again later");
        } else if (error instanceof ParseError) {
            showAlertDialog("Ah!", "Something went wrong, please try again later");
        }
    }
    public JSONArray createArray(ArrayList<QuizeListData>quizeListData){
        JSONArray array = new JSONArray();

        //Create json objects for two filter Ids
        JSONObject jsonParam = new JSONObject();


        try {

          for(int i=0;i<quizeListData.size();i++){
              jsonParam.put("quizeName", quizeListData.get(i).getQuizeName());
              jsonParam.put("date", quizeListData.get(i).getDate());
              jsonParam.put("winningamount", quizeListData.get(i).getWinningamount());
              jsonParam.put("id", quizeListData.get(i).getId());
              jsonParam.put("expireTime", quizeListData.get(i).getExpireTime());
              jsonParam.put("quizeDateTime", quizeListData.get(i).getQuizeDateTime());
              array.put(jsonParam);
          }
            //Add string params



        } catch (JSONException e) {
            e.printStackTrace();
        }



        return array;
    }

}

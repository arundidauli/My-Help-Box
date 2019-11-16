package com.diggingquiz.myquiz.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.*;
import com.diggingquiz.myquiz.R;
import com.diggingquiz.myquiz.Utils.Interface.ApiCallbackListener;
import com.diggingquiz.myquiz.Utils.preferences.PrefManager;
import com.quiz.diggingquiz.myquiz.Utils.server.ApiCallingMethods;
import com.quiz.diggingquiz.myquiz.Utils.server.Config;
import com.diggingquiz.myquiz.adapter.CurrentExamQuizeAdapter;
import com.diggingquiz.myquiz.base.BaseFragment;
import com.diggingquiz.myquiz.beans.QuizeListData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Tanuj yadav on 18/03/2018.
 */

public class CompleteQuizFragment extends BaseFragment implements ApiCallbackListener {
    public static String TAG=CompleteQuizFragment.class.getCanonicalName();

    private Context context;
    RecyclerView rv_currentQuiz;

    final ViewGroup nullParent = null;
    RecyclerView.Adapter adapter;
    ArrayList<Date> satSunArraylist;
    ArrayList<com.diggingquiz.myquiz.beans.QuizeListData> quizeListDataArrayList;
    public static ArrayList<com.diggingquiz.myquiz.beans.QuizeListData> quizeFilterArrayList;
    ArrayList<QuizeListData> deletedArrayList;
    int QuizeNo = 25;

    int count = 0;
    PrefManager prefManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aboutus, container, false);
        /*getActivity().setTitle("Home");*/

        initViews(view);
        prefManager = new PrefManager(getActivity());
        satSunArraylist = new ArrayList<>();
        quizeListDataArrayList = new ArrayList<>();
        quizeFilterArrayList = new ArrayList<>();
        deletedArrayList = new ArrayList<>();

        context = getActivity();

        getDateRange();
        getUpcomingQuize();
        return view;
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


    //this Method For count Saturday Sunday Count Beetween two date


    public void FilterData(String dateString1, String dateString2) {

        quizeFilterArrayList.clear();
        quizeFilterArrayList = (ArrayList<QuizeListData>) quizeListDataArrayList.clone();
        DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat df2 = new SimpleDateFormat("dd/MMM/yyyy");

        Date date1 = null;
        Date date2 = null;
        Date filterDate = null;
        for (int i = 0; i < quizeFilterArrayList.size(); i++) {
            try {
                date1 = df2.parse(dateString1);
                date2 = df2.parse(dateString2);
                filterDate = df2.parse(quizeFilterArrayList.get(i).getDate());

                if (CheckDate(date1, date2, filterDate)) {
                    // deletedArrayList.add(quotationFilterArrayList.get(i));
                    //sho
                    //showToast("Sucess");
                } else {
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
        Collections.sort(quizeFilterArrayList, new Comparator<QuizeListData>() {
            public int compare(QuizeListData m1, QuizeListData m2) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
                Date d1 = null;
                Date d2 = null;
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
      /*  adapter = new CurrentExamQuizeAdapter(getActivity(), quizeFilterArrayList,2);
        rv_currentQuiz.setAdapter(adapter);*/

    }

    public boolean CheckDate(Date min, Date max, Date FilterDate) {
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


        SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        //weekEndCount(begining,end);
        Calendar c = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DATE, 1);
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.MONTH, 1);


        DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
        Date d1 = null, d2 = null;
        try {
            d1 = df1.parse(prefManager.getActivatedate());
            d2 = df1.parse(prefManager.getExpireDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.quizeListDataArrayList = weekEndCount(d1, d2, Integer.valueOf(prefManager.getNoOfQuiz()));
        Date currentdate = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        // FilterData(simpleDateFormat.format(d1), simpleDateFormat.format(c.getTime()));

    }

    private void getUpcomingQuize() {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        //showProgressDialog("Fetchin AccountDetails");
        String strurl = Config.Upcoming + "" + prefManager.getUserName();
        ApiCallingMethods.requestForGet(strurl, getActivity(), this, progressDialog, "getupcoming");

    }

    @Override
    public void onResultCallback(String response, String flag) {
        Log.d(TAG, response);
        dismissDialog();
        try {
            System.out.print("Complete Quiz Response ================================"+response);

            if (flag != null && flag.equalsIgnoreCase("getupcoming")) {
                JSONObject objMain = null;

                objMain = new JSONObject(response);

                String merssge = objMain.getString("message");

               // System.out.println("Complete Quiz Response ================================"+response);

               // Toast.makeText(getActivity(), objMain.getString("message"), Toast.LENGTH_SHORT).show();
                JSONArray jsoncurrentquiz = new JSONArray(objMain.getString("CompletedQuizList"));
                for (int i = 0; i < jsoncurrentquiz.length(); i++) {
                    JSONObject obj = jsoncurrentquiz.getJSONObject(i);
                    QuizeListData quizeListData = new QuizeListData();
                    quizeListData.setDate(obj.getString("date"));
                    quizeListData.setExpireTime(Long.valueOf(obj.getString("expireTime")));
                    quizeListData.setQuizeDateTime(obj.getString("quizeDateTime"));
                    quizeListData.setQuizeName(obj.getString("quizeName"));
                    quizeListData.setWinningamount(Double.valueOf(obj.getString("winningamount")));
                    quizeListData.setId(Integer.valueOf(obj.getString("id")));
                    boolean isPass = Boolean.valueOf(obj.getString("IsPass"));
                    if (isPass) {
                        quizeListData.setIsPass(1);
                    } else {
                        quizeListData.setIsPass(0);
                    }

                quizeFilterArrayList.add(quizeListData);
            }
            adapter = new CurrentExamQuizeAdapter(getActivity(), quizeFilterArrayList, 2);
            rv_currentQuiz.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
    } catch(
    JSONException e)

    {
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
}

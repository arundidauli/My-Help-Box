package com.quiz.diggingquiz.myquiz.Utils.server;

/**
 * Created by Ankit chaudhary on 8/9/2017.
 */

public class Config {

    public static final String mainUrl="https://divinedrizzle.com/api/api/";
    public static final String topfiveQuestion = mainUrl+"TopFiveQuestion";
    public static final String getWithdrawlist = mainUrl+"GetWithDrawList/";
    public static final String getQuizDetailByUserId = mainUrl+"UserAccountDetailByUserId/";

    public static final String USER_LOGIN = mainUrl+"UserProfile";
    public static final String SubmitQuiZE = mainUrl+"InsertUserWinningAmount";

    public static final String allquizSubmit = mainUrl+"CreateUserQuiz";
    public static final String withdrawrequest = mainUrl+"WithdrawRequest";
    public static final String getUserAcccountDetails = mainUrl+"UserAccountDetailByUserId/";
    public static final String CurrentQuiz = mainUrl+"CurrentQuizByUserIdAndDate";
    public static final String Upcoming = mainUrl+"GetUpcommingAndCompletedQuizListByUserId/";
}
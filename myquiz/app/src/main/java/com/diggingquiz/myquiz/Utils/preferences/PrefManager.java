package com.diggingquiz.myquiz.Utils.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lalita Gill on 06/06/17.
 */

public class PrefManager {


    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;


    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "MyQuiz";

    private String IS_FIRST_TIME_LOGIN = "IsFirstTimeLogin";

    private String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    private String IS_PROFILE_CREATED = "isProfileCreated";
    private String userMobile = "user_mobile";
    private String userName = "user_name";
      private String user_pass = "user_pass";

    private String userid = "user_id";
    private String access_token = "access_token";

    private String generateOtp = "generateOtp";

    private String school_id = "school_id";
    private String DeviceCode="DeviceCode";
    private String isOtpVerify = "isOtpVerify";

    private String isSchoolOtpVerify = "isSchoolOtpVerify";

    private String userType = "userType";


    private String userSchoolMobile = "userSchoolMobile";
    private String schoollogoUrl = "schoollogoUrl";

    private String schoolName = "schoolName";
    private String schoolAddress = "schoolAddress";

    private String isTeacherOtpVerify = "isTeacherOtpVerify";

    private String TeacherMobile = "teacherMobile";

    private String isPrincipalOtpVerify = "isPrincipalOtpVerify";
    private String principlaMobile = "principlaMobile";


    private String isOperatorOtpVerify = "isOperatorOtpVerify";
    private String OperatorMobile = "OperatorMobile";
    private String studentClass = "studentClass";
    private String studentSection = "studentSection";


    private String principalName = "principalName";
    private String principalProfileImage = "principalProfileImage";

    private String teacherName = "teacherName";
    private String teacherProfileImage = "principalProfileImage";

    private String isGstEnable = "isGstEnable";
    private String imgCompanyUrl = "imgCompanyUrl";
    private String imgURl = "imgUrl";
    private String isService = "isService";

    private String financialYear = "financialYear";



    private String isRetail = "isRetail";

    private String defaultUnit = "defaultUnit";

    private String isCompositEnable = "isCompositEnable";

    private String activatedate = "Activatedate";
    private String NoOfQuiz = "NoOfQuiz";

    private String planName = "planeName";
    private String expireDate = "expireDate";

    private String walletAmount = "walletAmount";

    private String isQuestionSave = "questionSave";


    public String getisQuestionSave() {

        return pref.getString(isQuestionSave,"");
    }

    public void setisQuestionSave(String isQuestion) {
        editor.putString(this.isQuestionSave,isQuestion);
        editor.commit();

    }

    public String getWalletAmount() {

        return pref.getString(walletAmount,"");
    }

    public void setWalletAmount(String walletamount) {
        editor.putString(this.walletAmount,walletamount);
        editor.commit();

    }

    public String getExpireDate() {

        return pref.getString(expireDate,"");
    }

    public void setExpireDate(String expireDate) {
        editor.putString(this.expireDate,expireDate);
        editor.commit();

    }


    public String getActivatedate() {

        return pref.getString(activatedate,"");
    }

    public void setActivatedate(String activatedate) {
        editor.putString(this.activatedate,activatedate);
        editor.commit();

    }


    public String getNoOfQuiz() {

        return pref.getString(NoOfQuiz,"");
    }

    public void setNoOfQuiz(String noOfQuiz) {
        editor.putString(this.NoOfQuiz,noOfQuiz);
        editor.commit();

    }
    public String getPlanName() {

        return pref.getString(planName,"");
    }

    public void setPlanName(String planName) {
        editor.putString(this.planName,planName);
        editor.commit();

    }

    public String getImageUrl() {

        return pref.getString(imgURl,"");
    }

    public void setImageUrl(String imgurl) {
        editor.putString(this.imgURl,imgurl);
        editor.commit();

    }




    public int getIsCompositEnable() {
        return pref.getInt(isCompositEnable, 0);
    }

    public void setIsCompositEnable(int isComposit) {
        editor.putInt(isCompositEnable, isComposit);
        editor.commit();
    }

    public String getDefaultUnit() {

        return pref.getString(defaultUnit,"");
    }

    public void setDefaultUnit(String defaultUnit) {
        editor.putString(this.defaultUnit,defaultUnit);
        editor.commit();

    }

    public int getIsRetail() {
        return pref.getInt(isRetail, 0);
    }

    public void setIsRetail(int isretail) {
        editor.putInt(isRetail, isretail);
        editor.commit();
    }






    public String Datadeliver(String key) {
        return pref.getString(key,"");
    }

    public void Dataloader(String key,String value) {
        editor.putString(key,value);
        editor.commit();

    }


    public String getimgCompanyUrl() {
        return pref.getString(imgCompanyUrl,"");
    }

    public void setimgCompanyUrl(String imgCompanyUrl) {
        editor.putString(this.imgCompanyUrl,imgCompanyUrl);
        editor.commit();

    }


    public int getIsGstEnable() {
        return pref.getInt(isGstEnable, 0);
    }

    public void setIsGstEnable(int isGst) {
        editor.putInt(isGstEnable, isGst);
        editor.commit();
    }

    public String getDemo() {
        return pref.getString(Demo,"");
    }

    public void setDemo(String demo) {
        editor.putString(this.Demo,demo);
        editor.commit();

    }

    private String Demo = "Demo";




    public String getTeacherProfileImage() {
        return pref.getString(teacherProfileImage, "");
    }

    public void setTeacherProfileImage(String teacherProfileImage) {
        editor.putString(this.teacherProfileImage, teacherProfileImage);
        editor.commit();
    }

    public String getTeacherName() {
        return pref.getString(teacherName, "");
    }

    public void setTeacherName(String teacherName) {
        editor.putString(this.teacherName, teacherName);
        editor.commit();
    }

    public String getPrincipalProfileImage() {
        return pref.getString(principalProfileImage, "");
    }

    public void setPrincipalProfileImage(String principalProfileImage) {
        editor.putString(this.principalProfileImage, principalProfileImage);
        editor.commit();
    }

    public String getPrincipalName() {
        return pref.getString(principalName, "");
    }

    public void setPrincipalName(String principalName) {
        editor.putString(this.principalName, principalName);
        editor.commit();
    }


    public String getStudentSection() {
        return pref.getString(studentSection, "");
    }

    public void setStudentSection(String studentSection) {
        editor.putString(this.studentSection, studentSection);
        editor.commit();
    }


    public String getStudentClass() {
        return pref.getString(studentClass, "");
    }

    public void setStudentClass(String studentClass) {
        editor.putString(this.studentClass, studentClass);
        editor.commit();
    }


    public boolean getOperatorOtpVerify() {

        return pref.getBoolean(isOperatorOtpVerify, false);
    }

    public void setOperatorOtpVerify(boolean OperatorOtpVerify) {
        editor.putBoolean(isOperatorOtpVerify, OperatorOtpVerify);
        editor.commit();
    }

    public String getOperatorMobile() {
        return pref.getString(OperatorMobile, "");
    }

    public void setOperatorMobile(String OperatorMobile) {
        editor.putString(this.OperatorMobile, OperatorMobile);
        editor.commit();
    }

    public String getPrinciplaMobile() {
        return pref.getString(principlaMobile, "");
    }

    public void setPrinciplaMobile(String principlaMobile) {
        editor.putString(this.principlaMobile, principlaMobile);
        editor.commit();
    }

    public boolean getIsPrincipalOtpVerify() {

        return pref.getBoolean(isPrincipalOtpVerify, false);
    }

    public void setIsPrincipalOtpVerify(boolean isPrincipalotpVerify) {
        editor.putBoolean(isPrincipalOtpVerify, isPrincipalotpVerify);
        editor.commit();
    }

    public String getTeacherMobile() {
        return pref.getString(TeacherMobile, "");
    }

    public void setTeacherMobile(String TeacherMobile) {
        editor.putString(this.TeacherMobile, TeacherMobile);
        editor.commit();
    }

    public boolean getisTeacherOtpVerify() {
        return pref.getBoolean(isTeacherOtpVerify, false);
    }

    public void setisTeacherOtpVerify(boolean isTeacherotpVerify) {
        editor.putBoolean(isTeacherOtpVerify, isTeacherotpVerify);
        editor.commit();
    }

    public String getschoolName() {
        return pref.getString(schoolName, "");
    }

    public void setschoolName(String schoolName) {
        editor.putString(this.schoolName, schoolName);
        editor.commit();
    }

    public String getschoolAddress() {
        return pref.getString(schoolAddress, "");
    }

    public void setschoolAddress(String schoolAddress) {
        editor.putString(this.schoolAddress, schoolAddress);
        editor.commit();
    }


    public String getschoollogoUrl() {
        return pref.getString(schoollogoUrl, "");
    }

    public void setschoollogoUrl(String schoollogoUrl) {
        editor.putString(this.schoollogoUrl, schoollogoUrl);
        editor.commit();
    }

    public String getuserType() {
        return pref.getString(userType, "");
    }

    public void setuserType(String userType) {
        editor.putString(this.userType, userType);
        editor.commit();
    }

    public String getschool_id() {
        return pref.getString(school_id, "");
    }

    public void setschool_id(String school_id) {
        editor.putString(this.school_id, school_id);
        editor.commit();
    }

    public String getgenerateOtp() {
        return pref.getString(generateOtp, "");
    }

    public void setgenerateOtp(String generateOtp) {
        editor.putString(this.generateOtp, generateOtp);
        editor.commit();
    }

    public String getaccess_token() {
        return pref.getString(access_token, "");
    }

    public void setaccess_token(String access_token) {
        editor.putString(this.access_token, access_token);
        editor.commit();
    }

    private String userCarName="userCarName";
    private String userCarbrandimage="userCarbrandimage";


    public String getuser_Pass() {
        return pref.getString(user_pass, "");
    }

    public void setuser_Pass(String user_pass) {
        editor.putString(this.user_pass, user_pass);
        editor.commit();
    }


    public boolean isFirstTime() {
        return pref.getBoolean(IS_FIRST_TIME_LOGIN, false);
    }

    public boolean getIS_FIRST_TIME_LAUNCH() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, false);
    }

    public void setIS_FIRST_TIME_LAUNCH(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }



    public void setFirstTimeLogin(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LOGIN, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLogin() {
        return pref.getBoolean(IS_FIRST_TIME_LOGIN, true);
    }


    public boolean getIS_PROFILE_CREATED() {
        return pref.getBoolean(IS_PROFILE_CREATED, true);
    }

    public void setIS_PROFILE_CREATED(boolean IS_PROFILE_CREATED) {
        editor.putBoolean(this.IS_PROFILE_CREATED, IS_PROFILE_CREATED);
        editor.commit();
    }

    public String getUserMobile() {
        return pref.getString(userMobile, "");
    }

    public void setUserMobile(String userMobile) {
        editor.putString(this.userMobile, userMobile);
        editor.commit();
    }

    public String getUserName() {
        return pref.getString(userName, "");
    }

    public void setUserName(String userName) {
        editor.putString(this.userName, userName);
        editor.commit();
    }


    public String getuserid() {
        return pref.getString(userid, "");
    }

    public void setuserid(String user_id) {
        editor.putString(userid, user_id);
        editor.commit();
    }


    public String getDeviceCode() {
        return pref.getString(DeviceCode, "");
    }

    public void setDeviceCode(String deviceCode) {
        editor.putString(DeviceCode, deviceCode);
        editor.commit();
    }

    public boolean getisOtpVerify() {
        return pref.getBoolean(isOtpVerify, false);
    }

    public void setisOtpVerify(boolean isotpverify) {
        editor.putBoolean(isOtpVerify, isotpverify);
        editor.commit();
    }


    public boolean getisSchoolOtpVerify() {
        return pref.getBoolean(isSchoolOtpVerify, false);
    }

    public void setisSchoolOtpVerify(boolean isschoolOtpVerify) {
        editor.putBoolean(isSchoolOtpVerify, isschoolOtpVerify);
        editor.commit();
    }
    public String getUserSchoolMobileMobile() {
        return pref.getString(userSchoolMobile, "");
    }

    public void setUserSchoolMobileMobile(String UserSchoolMobileMobile) {
        editor.putString(this.userSchoolMobile, UserSchoolMobileMobile);
        editor.commit();
    }
}


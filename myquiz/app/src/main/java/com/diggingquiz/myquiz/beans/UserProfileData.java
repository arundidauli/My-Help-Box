package com.diggingquiz.myquiz.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tanuj yadav on 01/12/2017.
 */

public class UserProfileData implements Parcelable {


    private String activatedate;
    private String address;
    private String email;
    private String expiredate;
    private String membername;
    private String memberphoto;
    private int id;
    private int noOfQuiz;
    private int noOfQuestion;
    private  int noofcorrrectquestionforpass;
    private int planduration;
    private String plandurationtype;
    private String pin;
    private double winningamount;
    private String userName;
    private  String password;
    private String loweredUserName;
    private  String mobileAlias;
    private String isAnonymous;
    private String lastActivityDate;
    private String memberDetail;
    private  String aspnet_Applications;
    private String aspnet_Membership;

    public String getCheckquizalloted() {
        return checkquizalloted;
    }

    public void setCheckquizalloted(String checkquizalloted) {
        this.checkquizalloted = checkquizalloted;
    }

    private String checkquizalloted;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAcc_Name() {
        return acc_Name;
    }

    public void setAcc_Name(String acc_Name) {
        this.acc_Name = acc_Name;
    }

    public String getBank_Name() {
        return bank_Name;
    }

    public void setBank_Name(String bank_Name) {
        this.bank_Name = bank_Name;
    }

    private String accountNo;
    private String ifscCode;
    private  String acc_Name;
    private String bank_Name;

    public String getBrach_Name() {
        return brach_Name;
    }

    public void setBrach_Name(String brach_Name) {
        this.brach_Name = brach_Name;
    }

    private String brach_Name;

    public String getActivatedate() {
        return activatedate;
    }

    public void setActivatedate(String activatedate) {
        this.activatedate = activatedate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getMemberphoto() {
        return memberphoto;
    }

    public void setMemberphoto(String memberphoto) {
        this.memberphoto = memberphoto;
    }

    public int getNoOfQuiz() {
        return noOfQuiz;
    }

    public void setNoOfQuiz(int noOfQuiz) {
        this.noOfQuiz = noOfQuiz;
    }

    public int getNoOfQuestion() {
        return noOfQuestion;
    }

    public void setNoOfQuestion(int noOfQuestion) {
        this.noOfQuestion = noOfQuestion;
    }

    public int getNoofcorrrectquestionforpass() {
        return noofcorrrectquestionforpass;
    }

    public void setNoofcorrrectquestionforpass(int noofcorrrectquestionforpass) {
        this.noofcorrrectquestionforpass = noofcorrrectquestionforpass;
    }

    public int getPlanduration() {
        return planduration;
    }

    public void setPlanduration(int planduration) {
        this.planduration = planduration;
    }

    public String getPlandurationtype() {
        return plandurationtype;
    }

    public void setPlandurationtype(String plandurationtype) {
        this.plandurationtype = plandurationtype;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getWinningamount() {
        return winningamount;
    }

    public void setWinningamount(double winningamount) {
        this.winningamount = winningamount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoweredUserName() {
        return loweredUserName;
    }

    public void setLoweredUserName(String loweredUserName) {
        this.loweredUserName = loweredUserName;
    }

    public String getMobileAlias() {
        return mobileAlias;
    }

    public void setMobileAlias(String mobileAlias) {
        this.mobileAlias = mobileAlias;
    }

    public String isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(String anonymous) {
        isAnonymous = anonymous;
    }

    public String getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(String lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public String getMemberDetail() {
        return memberDetail;
    }

    public void setMemberDetail(String memberDetail) {
        this.memberDetail = memberDetail;
    }

    public String getAspnet_Applications() {
        return aspnet_Applications;
    }

    public void setAspnet_Applications(String aspnet_Applications) {
        this.aspnet_Applications = aspnet_Applications;
    }

    public String getAspnet_Membership() {
        return aspnet_Membership;
    }

    public void setAspnet_Membership(String aspnet_Membership) {
        this.aspnet_Membership = aspnet_Membership;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeString(activatedate);
        dest.writeString(address);
        dest.writeString(email);
        dest.writeString(expiredate);
        dest.writeInt(id);
        dest.writeString(membername);
        dest.writeString(memberphoto);
        dest.writeInt(noOfQuiz);
        dest.writeInt(noOfQuestion);
        dest.writeInt(noofcorrrectquestionforpass);
        dest.writeInt(planduration);
        dest.writeString(plandurationtype);
        dest.writeDouble(winningamount);
        dest.writeString(userName);
        dest.writeString(password);
        dest.writeString(loweredUserName);
        dest.writeString(mobileAlias);
        dest.writeString(isAnonymous);
        dest.writeString(lastActivityDate);
        dest.writeString(memberDetail);
        dest.writeString(aspnet_Applications);
        dest.writeString(aspnet_Membership);

        dest.writeString(acc_Name);
dest.writeString(accountNo);
dest.writeString(ifscCode);
dest.writeString(bank_Name);
dest.writeString(brach_Name);
dest.writeString(checkquizalloted);





    }
    public UserProfileData(Parcel in) {
        activatedate=in.readString();
        address=in.readString();
        email=in.readString();
    expiredate=in.readString();
        id=in.readInt();
        membername=in.readString();
        memberphoto=in.readString();
        noOfQuiz=in.readInt();
        noOfQuestion=in.readInt();
        noofcorrrectquestionforpass=in.readInt();
        planduration=in.readInt();
      plandurationtype=in.readString();
        winningamount=in.readDouble();
        userName=in.readString();
        password=in.readString();
        loweredUserName=in.readString();
        mobileAlias=in.readString();
        isAnonymous=in.readString();
        lastActivityDate=in.readString();
        memberDetail=in.readString();
        aspnet_Applications=in.readString();
        aspnet_Membership=in.readString();

        acc_Name=in.readString();
        accountNo=in.readString();
        ifscCode=in.readString();
        bank_Name=in.readString();
        brach_Name=in.readString();
        checkquizalloted=in.readString();

    }
    public static final Creator<UserProfileData> CREATOR = new Creator<UserProfileData>() {
        public UserProfileData createFromParcel(Parcel in) {
            return new UserProfileData(in);
        }

        public UserProfileData[] newArray(int size) {
            return new UserProfileData[size];
        }
    };
    public UserProfileData() {
    }
}

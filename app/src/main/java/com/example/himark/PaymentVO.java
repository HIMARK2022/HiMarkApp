package com.example.himark;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PaymentVO implements Serializable {
    private int requestNo;
    private String userName;
    private String userId;
    private String managerId;
    private String title;
    private String category;
    private String classifyNo;
    private String imp;
    private String file;
    private String content;
    private String period;
    private String rdate;
    private Date fdate;
    private Date cdate;
    private String state;
    private String reason;

    private List<String> filterList;
    private List<BoardAttachVO> attachList;


    public PaymentVO(String userId, String manager,String title, String category, String rdate, String state) {
        this.userId = userId;
        this.title = title;
        this.category = category;
        this.rdate = rdate;
        this.state = state;
        this.managerId = manager;
    }

    public PaymentVO(PaymentVO paymentVO) {
        this.userId = paymentVO.userId;
        this.title = paymentVO.title;
        this.category = paymentVO.category;
        this.rdate = paymentVO.rdate;
        this.state = paymentVO.state;
        this.managerId = paymentVO.managerId;
    }


    public int getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(int requestNo) {
        this.requestNo = requestNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClassifyNo() {
        return classifyNo;
    }

    public void setClassifyNo(String classifyNo) {
        this.classifyNo = classifyNo;
    }

    public String getImp() {
        return imp;
    }

    public void setImp(String imp) {
        this.imp = imp;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getRdate() {
       // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getFdate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

        return simpleDateFormat.format(fdate);
    }

    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

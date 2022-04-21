package com.example.himark;

import java.io.Serializable;

public class MemberVO implements Serializable {

    private String userId;
    private String username;

    private String duty;

    private String authority;
    private String currentstate;

    private String team;
    private String depart;

    private String head;

    public MemberVO(String userId, String username, String duty, String team, String depart, String head, String authority, String currentstate ) {
        this.userId = userId;
        this.username = username;
        this.duty = duty;
        this.authority = authority;
        this.currentstate = currentstate;
        this.team = team;
        this.depart = depart;
        this.head = head;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }



    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getCurrentstate() {
        return currentstate;
    }

    public void setCurrentstate(String currentstate) {
        this.currentstate = currentstate;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}

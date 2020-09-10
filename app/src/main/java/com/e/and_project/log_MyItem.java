package com.e.and_project;


public class log_MyItem {

    private String team_top;
    private String team_jg;
    private String team_mid;
    private String team_ad;
    private String team_sup;

    private String em_top;
    private String em_jg;
    private String em_mid;
    private String em_ad;
    private String em_sup;

    public log_MyItem(String team_top, String team_jg, String team_mid, String team_ad, String team_sup,
                      String em_top, String em_jg, String em_mid, String em_ad, String em_sup){
        this.team_top = team_top;   this.team_jg = team_jg; this.team_mid = team_mid;
        this.team_ad = team_ad; this.team_sup = team_sup;

        this.em_top = em_top; this.em_jg = em_jg; this.em_mid = em_mid;
        this.em_ad = em_ad; this.em_sup = em_sup;

    }


    public String getTeam_top() {
        return team_top;
    }

    public void setTeam_top(String team_top) {
        this.team_top = team_top;
    }

    public String getTeam_jg() {
        return team_jg;
    }

    public void setTeam_jg(String team_jg) {
        this.team_jg = team_jg;
    }

    public String getTeam_mid() {
        return team_mid;
    }

    public void setTeam_mid(String team_mid) {
        this.team_mid = team_mid;
    }

    public String getTeam_ad() {
        return team_ad;
    }

    public void setTeam_ad(String team_ad) {
        this.team_ad = team_ad;
    }

    public String getTeam_sup() {
        return team_sup;
    }

    public void setTeam_sup(String team_sup) {
        this.team_sup = team_sup;
    }

    public String getEm_top() {
        return em_top;
    }

    public void setEm_top(String em_top) {
        this.em_top = em_top;
    }

    public String getEm_jg() {
        return em_jg;
    }

    public void setEm_jg(String em_jg) {
        this.em_jg = em_jg;
    }

    public String getEm_mid() {
        return em_mid;
    }

    public void setEm_mid(String em_mid) {
        this.em_mid = em_mid;
    }

    public String getEm_ad() {
        return em_ad;
    }

    public void setEm_ad(String em_ad) {
        this.em_ad = em_ad;
    }

    public String getEm_sup() {
        return em_sup;
    }

    public void setEm_sup(String em_sup) {
        this.em_sup = em_sup;
    }
}
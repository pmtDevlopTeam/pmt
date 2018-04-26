package com.camelot.pmt.Statistics.model;

public class StatisticsUserCase {
    String version;
    String caseType;
    String dmandName;
    Long acount;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getDmandName() {
        return dmandName;
    }

    public void setDmandName(String dmandName) {
        this.dmandName = dmandName;
    }

    public Long getAcount() {
        return acount;
    }

    public void setAcount(Long acount) {
        this.acount = acount;
    }

    @Override
    public String toString() {
        return "StatisticsUserCase{" +
                "version='" + version + '\'' +
                ", caseType='" + caseType + '\'' +
                ", dmandName='" + dmandName + '\'' +
                ", acount=" + acount +
                '}';
    }
}

package com.camelot.pmt.project.model;

public class DemandWithBLOBs extends Demand {
    private String reqDesc;

    private String accpStandard;

    private String closeReason;

    public String getReqDesc() {
        return reqDesc;
    }

    public void setReqDesc(String reqDesc) {
        this.reqDesc = reqDesc == null ? null : reqDesc.trim();
    }

    public String getAccpStandard() {
        return accpStandard;
    }

    public void setAccpStandard(String accpStandard) {
        this.accpStandard = accpStandard == null ? null : accpStandard.trim();
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason == null ? null : closeReason.trim();
    }
}
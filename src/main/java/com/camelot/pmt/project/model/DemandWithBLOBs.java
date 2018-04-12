package com.camelot.pmt.project.model;

public class DemandWithBLOBs extends Demand {
    /**
     * 需求描述
     */
    private String demandDesc;

    /**
     * 关闭原因
     */
    private String closeReason;

    /**
     * 需求描述
     * @return demand_desc 需求描述
     */
    public String getDemandDesc() {
        return demandDesc;
    }

    /**
     * 需求描述
     * @param demandDesc 需求描述
     */
    public void setDemandDesc(String demandDesc) {
        this.demandDesc = demandDesc == null ? null : demandDesc.trim();
    }

    /**
     * 关闭原因
     * @return close_reason 关闭原因
     */
    public String getCloseReason() {
        return closeReason;
    }

    /**
     * 关闭原因
     * @param closeReason 关闭原因
     */
    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason == null ? null : closeReason.trim();
    }
}
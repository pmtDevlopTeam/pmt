package com.camelot.pmt.project.model;

import java.util.List;
import java.util.Map;

public class DemandVO {

    private Long demandId;
    private String userName;
    private String userStatus;
    private String userProRoleIds;
    private String roleId;
    private Integer page;
    private Integer size;
    private Map<String, Object> demandMap;
    private Demand demand;

    private List<Demand> demandList;

    public List<Demand> getDemandList() {
        return demandList;
    }

    public void setDemandList(List<Demand> demandList) {
        this.demandList = demandList;
    }

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    public Map<String, Object> getDemandMap() {
        return demandMap;
    }

    public void setDemandMap(Map<String, Object> demandMap) {
        this.demandMap = demandMap;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserProRoleIds() {
        return userProRoleIds;
    }

    public void setUserProRoleIds(String userProRoleIds) {
        this.userProRoleIds = userProRoleIds;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}

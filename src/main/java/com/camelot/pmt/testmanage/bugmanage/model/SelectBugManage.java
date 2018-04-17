package com.camelot.pmt.testmanage.bugmanage.model;


public class SelectBugManage{
	private Long id;
	
	private String bugNo;
	 //标题
	private String bugTitle;
	
    //指派人
    private String designatedId;
  
    //状态（0未确认 1已确认 2已解决 3已关闭  4已撤销）
    private String bugStatus;
    //创建者
    private String createUserId;
    //解决人
    private String solveId;
    //严重程度
    private String seriousDegree;
    
    //bug级别（优先级）
    private String bugLevel;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBugNo() {
		return bugNo;
	}
	public void setBugNo(String bugNo) {
		this.bugNo = bugNo;
	}
	public String getBugTitle() {
		return bugTitle;
	}
	public void setBugTitle(String bugTitle) {
		this.bugTitle = bugTitle;
	}
	public String getDesignatedId() {
		return designatedId;
	}
	public void setDesignatedId(String designatedId) {
		this.designatedId = designatedId;
	}
	public String getBugStatus() {
		return bugStatus;
	}
	public void setBugStatus(String bugStatus) {
		this.bugStatus = bugStatus;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getSeriousDegree() {
		return seriousDegree;
	}
	public void setSeriousDegree(String seriousDegree) {
		this.seriousDegree = seriousDegree;
	}
	public String getSolveId() {
		return solveId;
	}
	public void setSolveId(String solveId) {
		this.solveId = solveId;
	}
	public String getBugLevel() {
		return bugLevel;
	}
	public void setBugLevel(String bugLevel) {
		this.bugLevel = bugLevel;
	}
    
    
   
}
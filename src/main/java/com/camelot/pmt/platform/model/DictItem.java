package com.camelot.pmt.platform.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

public class DictItem implements Serializable {
    /**
     * @Description:字典项表
     *
     * @author pmt
     * @since 2018-04-11
     */
    private static final long serialVersionUID = 1L;
    /**
     * 默认索引 不可作用于业务
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 字典ID
     */
    private String dictId;
    /**
     * 字典项唯一32位UUID
     */
    private String dictItemId;
    /**
     * 字典项名称
     */
    private String dictItemName;
    /**
     * 字典项编码
     */
    private String dictItemCode;
    /**
     * 字典项值
     */
    private String dictItemValue;
    /**
     * 字典项状态 0（默认）启用 1 停用
     */
    private String state;
    /**
     * 排序号
     */
    private Integer sortNum;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createUserId;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 修改人
     */
    private String modifyUserId;
    /**
     * 用户对象信息
     */
    private User user;
    /**
     * 字典对象信息
     */
    private Dict dict;

    public Dict getDict() {
        return dict;
    }

    public void setDict(Dict dict) {
        this.dict = dict;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DictItem() {
        super();
        // TODO Auto-generated constructor stub
    }

    public DictItem(Long id, String dictId, String dictItemId, String dictItemName, String dictItemCode,
            String dictItemValue, String state, Integer sortNum, Date createTime, Date modifyTime, String createUserId,
            String modifyUserId) {
        super();
        this.id = id;
        this.dictId = dictId;
        this.dictItemId = dictItemId;
        this.dictItemName = dictItemName;
        this.dictItemCode = dictItemCode;
        this.dictItemValue = dictItemValue;
        this.state = state;
        this.sortNum = sortNum;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.createUserId = createUserId;
        this.modifyUserId = modifyUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictItemId() {
        return dictItemId;
    }

    public void setDictItemId(String dictItemId) {
        this.dictItemId = dictItemId;
    }

    public String getDictItemName() {
        return dictItemName;
    }

    public void setDictItemName(String dictItemName) {
        this.dictItemName = dictItemName;
    }

    public String getDictItemCode() {
        return dictItemCode;
    }

    public void setDictItemCode(String dictItemCode) {
        this.dictItemCode = dictItemCode;
    }

    public String getDictItemValue() {
        return dictItemValue;
    }

    public void setDictItemValue(String dictItemValue) {
        this.dictItemValue = dictItemValue;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    @Override
    public String toString() {
        return "DictItem [id=" + id + ", dictId=" + dictId + ", dictItemId=" + dictItemId + ", dictItemName="
                + dictItemName + ", dictItemCode=" + dictItemCode + ", dictItemValue=" + dictItemValue + ", state="
                + state + ", sortNum=" + sortNum + ", createTime=" + createTime + ", modifyTime=" + modifyTime
                + ", createUserId=" + createUserId + ", modifyUserId=" + modifyUserId + "]";
    }

}
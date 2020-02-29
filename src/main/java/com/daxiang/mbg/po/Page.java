package com.daxiang.mbg.po;

import com.daxiang.validator.annotation.NoDuplicateElement;
import com.daxiang.validator.group.UpdateGroup;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class Page implements Serializable {
    @NotNull(message = "id不能为空", groups = {UpdateGroup.class})
    private Integer id;

    /**
     * page名
     *
     * @mbg.generated
     */
    @NotBlank(message = "page名不能为空")
    private String name;

    /**
     * page所属项目
     *
     * @mbg.generated
     */
    @NotNull(message = "所属项目不能为空")
    private Integer projectId;

    /**
     * page所属分类
     *
     * @mbg.generated
     */
    private Integer categoryId;

    /**
     * page描述
     *
     * @mbg.generated
     */
    private String description;

    /**
     * 服务端保存的文件名
     *
     * @mbg.generated
     */
    private String imgName;

    /**
     * window高度
     *
     * @mbg.generated
     */
    private Integer windowHeight;

    /**
     * window宽度
     *
     * @mbg.generated
     */
    private Integer windowWidth;

    /**
     * 屏幕方向
     *
     * @mbg.generated
     */
    private String windowOrientation;

    /**
     * 图片所属的设备id
     *
     * @mbg.generated
     */
    private String deviceId;

    /**
     * 创建人id
     *
     * @mbg.generated
     */
    private Integer creatorUid;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 页面布局
     *
     * @mbg.generated
     */
    private String windowHierarchy;

    /**
     * 元素
     *
     * @mbg.generated
     */
    @Valid
    @NoDuplicateElement(message = "元素名不能重复")
    private java.util.List<com.daxiang.model.page.Element> elements;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Integer getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(Integer windowHeight) {
        this.windowHeight = windowHeight;
    }

    public Integer getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(Integer windowWidth) {
        this.windowWidth = windowWidth;
    }

    public String getWindowOrientation() {
        return windowOrientation;
    }

    public void setWindowOrientation(String windowOrientation) {
        this.windowOrientation = windowOrientation;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getCreatorUid() {
        return creatorUid;
    }

    public void setCreatorUid(Integer creatorUid) {
        this.creatorUid = creatorUid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getWindowHierarchy() {
        return windowHierarchy;
    }

    public void setWindowHierarchy(String windowHierarchy) {
        this.windowHierarchy = windowHierarchy;
    }

    public java.util.List<com.daxiang.model.page.Element> getElements() {
        return elements;
    }

    public void setElements(java.util.List<com.daxiang.model.page.Element> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", projectId=").append(projectId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", description=").append(description);
        sb.append(", imgName=").append(imgName);
        sb.append(", windowHeight=").append(windowHeight);
        sb.append(", windowWidth=").append(windowWidth);
        sb.append(", windowOrientation=").append(windowOrientation);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", creatorUid=").append(creatorUid);
        sb.append(", createTime=").append(createTime);
        sb.append(", windowHierarchy=").append(windowHierarchy);
        sb.append(", elements=").append(elements);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package io.bramcode.movie.moviecategoryservices.model;


import java.sql.Timestamp;


public class CategoryResponse {
    private Long categoryId;
    private String categoryName;
    private Boolean isActive;
    private Timestamp createDate;
    private String createBy;
    private Timestamp updateDate;
    private String updateBy;

    public CategoryResponse(){}

    public CategoryResponse(Long categoryId, String categoryName, Boolean isActive, Timestamp createDate, String createBy, Timestamp updateDate, String updateBy) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.isActive = isActive;
        this.createDate = createDate;
        this.createBy = createBy;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public CategoryResponse setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public CategoryResponse setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public CategoryResponse setIsActive(Boolean active) {
        isActive = active;
        return this;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public CategoryResponse setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public CategoryResponse setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public CategoryResponse setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public CategoryResponse setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }
}

package io.bramcode.movie.moviecategoryservices.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name="Category")
@Table(name="category")
public class Category implements Serializable {

    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_sequence"
    )
    @Column(
            name = "category_id",
            updatable = false
    )
    private Long categoryId;

    @Column(name = "category_name", length = 50, nullable = false)
    private String categoryName;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "create_date", nullable = false)
    @CreationTimestamp
    private Timestamp createDate;

    @Column(name = "create_by", length = 50, nullable = false)
    private String createBy;

    @Column(name = "update_date", nullable = false)
    @UpdateTimestamp
    private Timestamp updateDate;

    @NotNull(message = "Update_By can't be null")
    @Size(min = 1, max = 50, message = "Update_By length is 1-50")
    @Column(name = "update_by", length = 50, nullable = false)
    private String updateBy;

    public Category(){}

    public Category(Long categoryId, String categoryName, Boolean isActive,
                    Timestamp createDate, String createBy,
                    Timestamp updateDate, String updateBy) {
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

    public Category setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Category setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Category setIsActive(Boolean active) {
        isActive = active;
        return this;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public Category setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
        return this;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public Category setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public Category setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public Category setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", isActive=" + isActive +
                ", createDate=" + createDate +
                ", createBy='" + createBy + '\'' +
                ", updateDate=" + updateDate +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}

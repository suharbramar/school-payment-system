package sch.binadharma.spp.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;


@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Column(name = "create_by", length = 50, nullable = false)
    private String createBy;

    @Column(name = "create_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createDate;

    @Column(name = "update_date", nullable = false)
    @UpdateTimestamp
    private Timestamp updateDate;

//    @NotNull(message = "Update_By can't be null")
//    @Size(min = 1, max = 50, message = "Update_By length is 1-50")
    @Column(name = "update_by", length = 50, nullable = false)
    private String updateBy;




}

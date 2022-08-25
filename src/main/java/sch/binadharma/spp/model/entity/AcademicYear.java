package sch.binadharma.spp.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "AcademicYear")
@Table(name = "academicyear")
public class AcademicYear extends BaseEntity {
    @Id
    @Column(name = "academic_id", length = 50, nullable = false)
    private String academicId;

    @Column(name = "academic_name", length = 100, nullable = false)
    private String academicName;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "isDeleted", nullable = false)
    private Boolean isDeleted;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "academic_startdate", nullable = false)
    private Date academicStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "academic_enddate", nullable = false)
    private Date academicEndDate;

    @Column(name = "academic_note", length = 100)
    private String academicNote;

}

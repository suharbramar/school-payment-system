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
@Entity(name = "Student")
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @Size(min = 1, max = 10, message = "Maximum student nis is 10 digit number")
    @Column(name = "student_nis", length = 10, nullable = false)
    private Long studentNis;

    @Column(name = "student_name", length = 50, nullable = false)
    private String studentName;

    @Column(name = "student_class", length = 5, nullable = false)
    private String studentClass;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "student_date_of_birth", nullable = false)
    private Date studentDateOfBirth;

    @Column(name = "student_school_type", length = 10, nullable = false)
    private String studentSchoolType;

    @Column(name = "student_school_name", length = 50, nullable = false)
    private String studentSchoolName;

    @Column(name = "major", length = 10)
    private String studentMajor;

    @Column(name = "student_originator_school_name", length = 50)
    private String studentOriginatorSchoolName;

    @Column(name = "student_address", length = 100)
    private String studentAddress;

    @Column(name = "student_phone_number", length = 20)
    private String studentPhoneNumber;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "isDeleted", nullable = false)
    private Boolean isDeleted;

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


}

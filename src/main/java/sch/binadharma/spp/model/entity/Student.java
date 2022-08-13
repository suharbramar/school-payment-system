package sch.binadharma.spp.model.entity;

import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Student")
@Table(name = "student")
public class Student extends BaseEntity {

    @Id
    @Size(min = 1, max = 10, message = "Maximum student nis is 10 digit number")
    @Column(name = "student_nis", length = 10, nullable = false)
    private Long studentNis;

    @Column(name = "student_name", length = 50, nullable = false)
    private String studentName;

    @Column(name = "student_class", length = 5, nullable = false)
    private String studentClass;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "student_date_of_birth", nullable = false)
    private Date studentDateOfBirth;

    @Column(name = "student_gender", length = 10, nullable = false)
    private String studentGender;

    @Column(name = "student_address", length = 100, nullable = false)
    private String studentAddress;

    @Column(name = "student_city", length = 50, nullable = false)
    private String studentCity;

    @Column(name = "student_province", length = 50, nullable = false)
    private String studentProvince;

    @Column(name = "student_zip", length = 10, nullable = false)
    private String studentZip;

    @Column(name = "student_phone_number", length = 20)
    private String studentPhoneNumber;

    @Column(name = "studentStatus", length = 20, nullable = false)
    private String studentStatus;

    @Column(name = "student_father_name", length = 50, nullable = false)
    private String studentFatherName;

    @Column(name = "student_mother_name", length = 50, nullable = false)
    private String studentMotherName;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "student_sameaddress_withparents", nullable = false)
    private Boolean IsSameAddressWithParents;

    @Column(name = "student_parent_address", length = 100, nullable = false)
    private String studentParentAddress;

    @Column(name = "student_parent_city", length = 50, nullable = false)
    private String studentParentCity;

    @Column(name = "student_parent_province", length = 50, nullable = false)
    private String studentParentProvince;

    @Column(name = "student_parent_zip", length = 10, nullable = false)
    private String studentParentZip;

    @Column(name = "student_parent_phone_number", length = 20)
    private String studentParentPhoneNumber;

    @Column(name = "student_school_name", length = 50, nullable = false)
    private String studentSchoolName;

    @Column(name = "major", length = 10)
    private String studentMajor;

    @Column(name = "student_originator_school_name", length = 50)
    private String studentOriginatorSchoolName;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

}

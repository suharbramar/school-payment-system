package sch.binadharma.spp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Student")
@Table(name = "student")
public class Student extends BaseEntity {

    @Id
    @Size(min = 1, max = 10, message = "Maximum student nisn is 10 digit number")
    @Column(name = "student_nisn", length = 10, nullable = false)
    private String studentNisn;

    @Column(name = "student_full_name", length = 50, nullable = false)
    private String studentFullName;

    @Column(name = "student_nick_name", length = 50)
    private String studentNickName;

    @Column(name = "student_gender", length = 10)
    @NotEmpty(message = "Gender can't be empty!")
    private String studentGender;

    @Column(name = "student_place_dob", length = 50, nullable = false)
    private String studentPlaceDob;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "student_date_of_birth", nullable = false)
    private Date studentDateOfBirth;

    @Column(name = "student_address", length = 100, nullable = false)
    private String studentAddress;

    @Column(name = "student_city", length = 50, nullable = false)
    private String studentCity;

    @Column(name = "student_province", length = 50, nullable = false)
    private String studentProvince;

    @Column(name = "student_zip", length = 10)
    private String studentZip;

    @Column(name = "student_address_type", length = 10, nullable = false)
    private String studentAddressType;

    @Column(name = "student_phone_number", length = 20)
    private String studentPhoneNumber;

    @Column(name = "student_email", length = 30)
    private String studentEmail;

    @Column(name = "studentStatus", length = 20, nullable = false)
    private String studentStatus;

    @Column(name = "student_school_name", length = 30, nullable = false)
    private String studentSchoolName;

    @Column(name = "student_major", length = 10)
    private String studentMajor;

    @Column(name = "student_class", length = 5, nullable = false)
    private String studentClass;

    @Column(name = "student_father_name", length = 50)
    private String studentFatherName;

    @Column(name = "student_father_date_of_birth")
    private String studentFatherDateOfBirth;

    @Column(name = "student_father_job", length = 10)
    private String studentFatherJob;

    @Column(name = "student_father_income_range", length = 30)
    private String studentFatherIncomeRange;

    @Column(name = "student_mother_name", length = 50)
    private String studentMotherName;

    @Column(name = "student_mother_date_of_birth")
    private String studentMotherDateOfBirth;

    @Column(name = "student_mother_job")
    private String studentMotherJob;

    @Column(name = "student_mother_income_range")
    private String studentMotherIncomeRange;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "isStudentKps", nullable = false)
    private Boolean isStudentKps;

    @Column(name = "studentKps", length = 18)
    private String studentKpsNumber;

    @Column(name = "academic_id", length = 50, nullable = false)
    private String academicId;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

}

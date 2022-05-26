package sch.binadharma.spp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Student")
@Table(name = "student")
public class Spp implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "spp_id", updatable = false, nullable = false)
    private UUID sppId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_nis", referencedColumnName = "student_nis")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_id", referencedColumnName = "academic_id")
    private AcademicYear academicYear;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "ppdb_formulir", updatable = false)
    private BigDecimal ppdbFormulir;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "ppdb_osis", updatable = false)
    private BigDecimal ppdbOsis;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "ppdb_spp_july", updatable = false)
    private BigDecimal ppdbSppJuly;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "ppdb_dsp", updatable = false)
    private BigDecimal ppdbDsp;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "registrasi_sarana", updatable = false)
    private BigDecimal registrasiSarana;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "registrasi_osis", updatable = false)
    private BigDecimal registrasiOsis;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "registrasi_spp_july")
    private BigDecimal registrasiSppJuly;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "spp_agustus")
    private BigDecimal sppAgustus;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "spp_september")
    private BigDecimal sppSeptember;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "spp_october")
    private BigDecimal sppOctober;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "spp_november")
    private BigDecimal sppNovember;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "spp_december")
    private BigDecimal sppDecember;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "spp_january")
    private BigDecimal sppJanuary;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "spp_february")
    private BigDecimal sppFebruary;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "spp_march")
    private BigDecimal sppMarch;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "spp_april")
    private BigDecimal sppApril;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "spp_may")
    private BigDecimal sppMay;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "spp_june")
    private BigDecimal sppJune;

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

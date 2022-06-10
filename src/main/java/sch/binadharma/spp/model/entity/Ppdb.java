package sch.binadharma.spp.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
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
@Builder
@Entity(name = "Ppdb")
@Table(name = "ppdb")
public class Ppdb implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ppdb_id", updatable = false, nullable = false)
    private UUID ppdbId;

    @ManyToOne
    @JoinColumn(name = "student_nis", referencedColumnName = "student_nis", updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "ppdb_name", referencedColumnName = "finance_config_id", updatable = false)
    private FinanceConfig financeConfig;

    @DecimalMin(value = "0.00")
    @Digits(integer = 15, fraction = 2)
    @Column(name = "ppdb_amount", nullable = false)
    private BigDecimal ppdbAmount;

    @DecimalMin(value = "0.00")
    @Digits(integer = 15, fraction = 2)
    @Column(name = "student_ppdb_paid", nullable = false)
    private BigDecimal studentPpdbPaid;

    @Column(name = "student_ppdb_status", length = 50, nullable = false)
    private String studentPpdbStatus;

    @Column(name = "student_ppdb_note", length = 100)
    private String studentPpdbNote;

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

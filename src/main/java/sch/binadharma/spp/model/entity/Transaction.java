package sch.binadharma.spp.model.entity;

import lombok.*;
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
@Builder
@Entity(name = "Transaction")
@Table(name = "transaction")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "transaction_id", updatable = false, nullable = false)
    private UUID transactionId;

    @Size(min = 1, max = 10, message = "Maximum student nis is 10 digit number")
    @Column(name = "student_nis", length = 10, nullable = false)
    private Long studentNis;

    @Column(name = "transaction_date", nullable = false)
    @CreationTimestamp
    private Timestamp transactionDate;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "transaction_amount")
    private BigDecimal transactionAmount;

    @Column(name = "transaction_type", length = 50, nullable = false)
    private String transactionType;

    @Column(name = "transaction_status", length = 50)
    private String transactionStatus;

    @Column(name = "transaction_note", length = 100)
    private String transactionNote;

    @Column(name = "academic_id", length = 50, nullable = false)
    private String academicId;

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

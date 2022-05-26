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
@Entity(name = "SppTransaction")
@Table(name = "spptransaction")
public class SppTransaction implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "spp_transaction_id", updatable = false, nullable = false)
    private UUID sppTransactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "finance_config_id", referencedColumnName = "finance_config_id")
    private FinanceConfig financeConfig;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_nis", referencedColumnName = "student_nis")
    private Student student;

    @Column(name = "transaction_date", nullable = false)
    @CreationTimestamp
    private Timestamp transactionDate;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @Column(name = "transaction_amount")
    private BigDecimal transactionAmount;

    @Column(name = "create_by", length = 50, nullable = false)
    private String createBy;

    @Column(name = "update_date", nullable = false)
    @UpdateTimestamp
    private Timestamp updateDate;

    @NotNull(message = "Update_By can't be null")
    @Size(min = 1, max = 50, message = "Update_By length is 1-50")
    @Column(name = "update_by", length = 50, nullable = false)
    private String updateBy;

    @Column(name = "transaction_type", length = 20)
    private String transaction_type;

    @Column(name = "transaction_status", length = 50)
    private String transaction_status;

    @Column(name = "transaction_note", length = 100)
    private String transaction_note;

}

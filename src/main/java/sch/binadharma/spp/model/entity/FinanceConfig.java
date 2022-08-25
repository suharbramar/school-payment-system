package sch.binadharma.spp.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "FinanceConfig")
@Table(name = "financeconfig")
public class FinanceConfig implements Serializable {

    @Id
    @Column(name = "finance_config_id", length = 100, updatable = false, nullable = false)
    private String financeConfigId;

    @Column(name = "finance_name", length = 50, nullable = false)
    private String financeName;

    @Column(name = "finance_config_description", length = 100, nullable = false)
    private String financeConfigDescription;

    @Column(name = "finance_credit_debit", length = 2, nullable = false)
    private String financeCreditDebit;

    @Column(name = "finance_school_type", length = 10, nullable = false)
    private String financeSchoolType;

    @DecimalMin(value = "0.00")
    @Digits(integer = 15, fraction = 2)
    @Column(name = "finance_value", nullable = false)
    private BigDecimal financeValue;

    @Column(name = "financeType", length = 20, nullable = false)
    private String financeType;

    @ManyToOne
    @JoinColumn(name = "academic_id", referencedColumnName = "academic_id", updatable = false)
    private AcademicYear academicYear;

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

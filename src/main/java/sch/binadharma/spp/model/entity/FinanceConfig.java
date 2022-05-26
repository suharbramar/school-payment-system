package sch.binadharma.spp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "FinanceConfig")
@Table(name = "financeconfig")
public class FinanceConfig implements Serializable {

    @Id
    @Column(name = "finance_config_id", length = 20, updatable = false, nullable = false)
    private String financeConfigId;

    @Column(name = "finance_config_name", length = 50, nullable = false)
    private String financeConfigName;

    @Column(name = "finance_credit_debit", length = 2, nullable = false)
    private String financeCreditDebit;

    @Column(name = "finance_value", length = 20)
    private String financeValue;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "finance_status", nullable = false)
    private Boolean finance_status;

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

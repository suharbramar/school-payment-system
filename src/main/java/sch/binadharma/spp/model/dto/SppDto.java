package sch.binadharma.spp.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SppDto implements Serializable {
    private UUID sppId;
    private Long studentNis;

    private String createBy;
    private String updateBy;
}

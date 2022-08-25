package sch.binadharma.spp.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sch.binadharma.spp.model.entity.FinanceConfig;
import sch.binadharma.spp.service.FinanceConfigService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/v1/financeconfig")
public class FinanceConfigController {

    @Autowired
    FinanceConfigService financeConfigService;

    @GetMapping("/all")
    public List<FinanceConfig> retreiveAllFinanceConfig() {
        return financeConfigService.retreiveAllFinanceConfig();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFinanceConfigById(@PathVariable(value = "id") String financeConfigId) throws NotFoundException {
        FinanceConfig financeConfig = financeConfigService.retreiveByFinanceConfigId(financeConfigId);
        if (Objects.nonNull(financeConfig)) {
            return new ResponseEntity<>(financeConfig, HttpStatus.OK);
        }
        return new ResponseEntity<>("Finance Config not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public FinanceConfig createFinanceConfig(@Valid @RequestBody FinanceConfig financeConfig) {
        return financeConfigService.saveFinanceConfig(financeConfig);
    }

    @PutMapping("/update/{id}")
    public FinanceConfig updateFinanceConfig(@PathVariable(value = "id") String financeConfigId,
                                             @Valid @RequestBody FinanceConfig financeConfig) {
        return financeConfigService.updateFinanceConfig(financeConfigId, financeConfig);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteFinanceConfig(@PathVariable(value = "id") String financeConfigId) {
        return financeConfigService.deleteFinanceConfig(financeConfigId);
    }

}

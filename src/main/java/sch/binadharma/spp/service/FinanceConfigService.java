package sch.binadharma.spp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sch.binadharma.spp.model.entity.FinanceConfig;
import sch.binadharma.spp.repository.FinanceConfigRepository;

import java.util.*;

@Service
@Slf4j
public class FinanceConfigService {

    //private static final Logger logger = (Logger) LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private FinanceConfigRepository financeConfigRepository;

    public FinanceConfigService() {
    }

    public List<FinanceConfig> retreiveAllFinanceConfig() {
        return financeConfigRepository.findAll();
    }

    public FinanceConfig retreiveByFinanceConfigId(String financeConfigId) {
        //logger.info("Inside category response..");

        Optional<FinanceConfig> optionalFinanceConfig = financeConfigRepository.findById(financeConfigId);

        if (optionalFinanceConfig.isPresent()) {
            FinanceConfig responseFinanceConfig = optionalFinanceConfig.get();

            return responseFinanceConfig;
        }

        return null;

    }

    public FinanceConfig saveFinanceConfig(FinanceConfig financeConfig) {
        return financeConfigRepository.save(financeConfig);
    }

    public FinanceConfig updateFinanceConfig(String financeConfigId, FinanceConfig financeConfig) {
        Optional<FinanceConfig> optFinanceConfig = financeConfigRepository.findById(financeConfigId);

        if (optFinanceConfig.isPresent()) {
            FinanceConfig updFinanceConfig = optFinanceConfig.get();
            updFinanceConfig.setFinanceConfigDescription(financeConfig.getFinanceConfigDescription());
            updFinanceConfig.setFinanceCreditDebit(financeConfig.getFinanceCreditDebit());
            updFinanceConfig.setFinanceValue(financeConfig.getFinanceValue());
            updFinanceConfig.setAcademicYear(updFinanceConfig.getAcademicYear());
            updFinanceConfig.setIsDeleted(financeConfig.getIsDeleted());
            updFinanceConfig.setUpdateBy("ADMIN");

            financeConfigRepository.save(updFinanceConfig);

            return updFinanceConfig;

        } else {
            ResponseEntity.notFound();
        }

        return null;
    }

    public Map<String, Boolean> deleteFinanceConfig(String financeConfigId) {
        FinanceConfig deleteFinanceConfig = retreiveByFinanceConfigId(financeConfigId);

        Map<String, Boolean> response = new HashMap<>();
        if (Objects.nonNull(deleteFinanceConfig)) {
            financeConfigRepository.delete(deleteFinanceConfig);
            response.put("Deleted", Boolean.TRUE);
        } else {
            ResponseEntity.notFound();
        }

        return response;
    }

}

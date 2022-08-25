package sch.binadharma.spp.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sch.binadharma.spp.model.entity.FinanceConfig;
import sch.binadharma.spp.model.entity.Ppdb;
import sch.binadharma.spp.model.entity.Spp;
import sch.binadharma.spp.repository.FinanceConfigRepository;
import sch.binadharma.spp.repository.PpdbRepository;
import sch.binadharma.spp.repository.SppRepository;
import sch.binadharma.spp.repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class PpdbService {

    //private static final Logger logger = (Logger) LoggerFactory.getLogger(StudentService.class);

    private static final String SPP_SMA_BDTHN2021_2022 = "SPP_SMA_BDTHN2021-2022";

    @Autowired
    private PpdbRepository ppdbRepository;

    @Autowired
    private SppRepository sppRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private FinanceConfigRepository financeConfigRepository;

    @Autowired
    private FinanceConfigService financeConfigService;

    @Autowired
    private SppService sppService;

    public PpdbService() {
    }

    public List<Ppdb> retreiveAllPpdb() {
        return ppdbRepository.findAll();

    }

    public Ppdb retreiveByPpdbId(UUID ppdbId) {
        //logger.info("Inside category response..");

        Optional<Ppdb> optionalPpdb = ppdbRepository.findById(ppdbId);

        if (optionalPpdb.isPresent()) {
            Ppdb ppdb = optionalPpdb.get();

            return ppdb;
        }

        return null;

    }

    @Transactional
    public Ppdb newPpdb(Ppdb ppdb) {

        FinanceConfig financeConfig = financeConfigService.retreiveByFinanceConfigId(ppdb.getFinanceConfig().getFinanceConfigId());

        //Init spp untuk siswa baru
        Integer countExist = sppRepository.countExistingStudentNisAndAcademicYear(ppdb.getStudent().getStudentNisn(), financeConfig.getAcademicYear().getAcademicId());

        if (countExist.intValue() == 0) {

            List<FinanceConfig> financeConfigList = financeConfigRepository.findByFinanceTypeAndAcademicId("SPP", financeConfig.getAcademicYear().getAcademicId());

            List<Spp> sppList = new ArrayList<>();
            financeConfigList.stream().forEach(
                    financeConfigItem -> {
                        Spp newSpp = Spp.builder()
                                .studentId(ppdb.getStudent())
                                .sppName(financeConfigItem.getFinanceName())
                                .sppAmount(Objects.nonNull(financeConfigItem) ? financeConfigItem.getFinanceValue() : new BigDecimal(0.00))
                                .studentSppStatus("BELUM LUNAS")
                                .studentSppNote(Strings.EMPTY)
                                .academicYear(Objects.nonNull(financeConfigItem) ? financeConfigItem.getAcademicYear() : null)
                                .isDeleted(false)
                                .createBy("PPDB ADMIN")
                                .updateBy("PPDB ADMIN")
                                .build();
                        sppList.add(newSpp);
                    }
            );

            if (!sppList.isEmpty()) {
                sppRepository.saveAll(sppList);
            }

        }


        Ppdb newPpdb = Ppdb.builder()
                .student(ppdb.getStudent())
                .financeConfig(financeConfig)
                .ppdbAmount(Objects.nonNull(financeConfig) ? financeConfig.getFinanceValue() : new BigDecimal(0.00))
                .studentPpdbPaid(ppdb.getStudentPpdbPaid())
                .studentPpdbStatus(ppdb.getStudentPpdbStatus())
                .studentPpdbNote(ppdb.getStudentPpdbNote())
                .isDeleted(ppdb.getIsDeleted())
                .createBy(ppdb.getCreateBy())
                .updateBy(ppdb.getUpdateBy())
                .build();

        return ppdbRepository.save(newPpdb);
    }

    @Transactional
    public Ppdb updatePpdb(UUID ppdbId, Ppdb requestPpdb) {
        Optional<Ppdb> existingPpdb = ppdbRepository.findById(ppdbId);

        if (existingPpdb.isPresent()) {
            Ppdb updatePpdb = existingPpdb.get();

            updatePpdb.setStudentPpdbPaid(updatePpdb.getStudentPpdbPaid().add(requestPpdb.getStudentPpdbPaid()));
            updatePpdb.setStudentPpdbStatus(requestPpdb.getStudentPpdbStatus());
            updatePpdb.setStudentPpdbNote(requestPpdb.getStudentPpdbNote());
            updatePpdb.setUpdateBy("ADMIN");

            return ppdbRepository.save(updatePpdb);

        } else {
            ResponseEntity.notFound();
        }

        return null;
    }


//    public void insertSppTransaction(Spp spp, String transactionType, String transactionStatus) {
//
//        BigDecimal transactionAmount = getTransactionAmout(spp, transactionType);
//
//        Transaction transaction = Transaction.builder()
//                .sppId(spp.getSppId())
//                .studentNis(spp.getStudent().getStudentNis())
//                .academicId(spp.getAcademicYear().getAcademicId())
//                .transactionAmount(transactionAmount)
//                .transactionType(transactionType)
//                .transactionStatus(transactionStatus.equalsIgnoreCase("P") ? "SUBMITED" : transactionStatus.equalsIgnoreCase("C") ? "CANCELED" : StringUtils.EMPTY)
//                .transactionNote("BAYAR SPP")
//                .createBy("ADMIN")
//                .updateBy("ADMIN")
//                .build();
//
//        sppTransactionRepository.save(transaction);
//
//
//    }

//    public BigDecimal getTransactionAmout(Spp spp, String transactionType) {
//        if (StringUtils.isNotEmpty(transactionType)) {
//            if (StringUtils.startsWithIgnoreCase(transactionType, "ppdb")) {
//
//                //return spp.getPpdbDsp().add(spp.getPpdbFormulir()).add(spp.getPpdbOsis()).add(spp.getPpdbSppJuly());
//            } else if (StringUtils.startsWithIgnoreCase(transactionType, "registrasi")) {
//                return spp.getRegistrasiOsis().add(spp.getRegistrasiSarana()).add(spp.getRegistrasiSppJuly());
//            } else if (StringUtils.startsWithIgnoreCase(transactionType, "spp")) {
//                return spp.getSppAgustus().add(spp.getSppSeptember()).add(spp.getSppOctober())
//                        .add(spp.getSppNovember()).add(spp.getSppDecember()).add(spp.getSppJanuary()).add(spp.getSppFebruary())
//                        .add(spp.getSppMarch()).add(spp.getSppApril()).add(spp.getSppMay()).add(spp.getSppJune());
//            } else {
//                return new BigDecimal(0);
//            }
//        } else {
//            throw new RuntimeException("Transaction type is not valid");
//        }
//    }

    public Map<String, Boolean> deletePpdb(UUID ppdbId) {
        Ppdb deletePpdb = retreiveByPpdbId(ppdbId);

        Map<String, Boolean> response = new HashMap<>();
        if (Objects.nonNull(deletePpdb)) {
            ppdbRepository.delete(deletePpdb);
            response.put("Deleted", Boolean.TRUE);
        } else {
            ResponseEntity.notFound();
        }

        return response;
    }

}

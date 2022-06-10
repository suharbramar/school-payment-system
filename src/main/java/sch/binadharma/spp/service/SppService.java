package sch.binadharma.spp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sch.binadharma.spp.model.entity.Spp;
import sch.binadharma.spp.repository.FinanceConfigRepository;
import sch.binadharma.spp.repository.SppRepository;
import sch.binadharma.spp.repository.TransactionRepository;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class SppService {

    //private static final Logger logger = (Logger) LoggerFactory.getLogger(StudentService.class);

    private static final String SPP_SMA_BDTHN2021_2022 = "SPP_SMA_BDTHN2021-2022";

    @Autowired
    private SppRepository sppRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private FinanceConfigRepository financeConfigRepository;

    @Autowired
    private FinanceConfigService financeConfigService;

    public SppService() {
    }

    public List<Spp> retreiveAllSpp() {
        return sppRepository.findAll();
    }

    public Spp retreiveBySppId(UUID sppId) {
        //logger.info("Inside category response..");

        Optional<Spp> optionalSpp = sppRepository.findById(sppId);

        if (optionalSpp.isPresent()) {
            Spp responseStudentSpp = optionalSpp.get();

            return responseStudentSpp;
        }

        return null;

    }

    @Transactional
    public Spp newSpp(Spp spp) {
        Long id =  spp.getStudentId().getStudentNis();
        Optional<Spp> optionalSpp = sppRepository.findBySppNameAndAcademicYearAndStudentIdAndIsDeleted(spp.getSppName(), spp.getAcademicYear(),
                spp.getStudentId(), Boolean.FALSE);

        if(optionalSpp.isEmpty()){
            return sppRepository.save(spp);
        }

        throw new RuntimeException("Spp is already exist for inputed academic");
    }


    @Transactional
    public Spp updateSpp(UUID sppId, Spp spp) {
        Optional<Spp> existingSpp = sppRepository.findById(sppId);

        if (existingSpp.isPresent()) {
            Spp updateSpp = existingSpp.get();
            updateSpp.setStudentSppStatus(spp.getStudentSppStatus());
            updateSpp.setStudentSppNote(spp.getStudentSppNote());
            updateSpp.setUpdateBy(spp.getUpdateBy());

            return sppRepository.save(updateSpp);

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

    public Map<String, Boolean> deleteSpp(UUID sppId) {
        Spp deleteSpp = retreiveBySppId(sppId);

        Map<String, Boolean> response = new HashMap<>();
        if (Objects.nonNull(deleteSpp)) {
            sppRepository.delete(deleteSpp);
            response.put("Deleted", Boolean.TRUE);
        } else {
            ResponseEntity.notFound();
        }

        return response;
    }

}

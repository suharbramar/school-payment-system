package sch.binadharma.spp.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sch.binadharma.spp.model.entity.Spp;
import sch.binadharma.spp.service.SppService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/v1/spp")
public class SppController {

    @Autowired
    SppService sppService;

    @GetMapping("/all")
    public List<Spp> retreiveAllSppInfo() {
        return sppService.retreiveAllSpp();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSppById(@PathVariable(value = "id") UUID sppId) throws NotFoundException {
        Spp spp = sppService.retreiveBySppId(sppId);
        if (Objects.nonNull(spp)) {
            return new ResponseEntity<>(spp, HttpStatus.OK);
        }
        return new ResponseEntity<>("Spp not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public Spp createSpp(@Valid @RequestBody Spp spp, @RequestParam(name = "transaction_type", required = false) String transactionType, @RequestParam(name = "transaction_status", required = false) String transactionStatus) {
        return sppService.newSpp(spp);
        //return sppService.saveSpp(spp, transactionType, transactionStatus);
    }

    @PutMapping("/update/{id}")
    public Spp updateSpp(@PathVariable(value = "id") UUID sppId,
                         @Valid @RequestBody Spp sppDetails) {

        return sppService.updateSpp(sppId, sppDetails);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteSpp(@PathVariable(value = "id") UUID sppId) {
        return sppService.deleteSpp(sppId);
    }


}

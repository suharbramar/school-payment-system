package sch.binadharma.spp.restcontroller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sch.binadharma.spp.model.entity.Ppdb;
import sch.binadharma.spp.service.PpdbService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ppdb")
public class PpdbResource {

    @Autowired
    PpdbService ppdbService;

    @GetMapping("/all")
    public List<Ppdb> retreiveAllPpdbInfo() {
        return ppdbService.retreiveAllPpdb();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPpdbById(@PathVariable(value = "id") UUID ppdbId) throws NotFoundException {
        Ppdb ppdb = ppdbService.retreiveByPpdbId(ppdbId);
        if (Objects.nonNull(ppdb)) {
            return new ResponseEntity<>(ppdb, HttpStatus.OK);
        }
        return new ResponseEntity<>("Ppdb not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public Ppdb createPpdb(@Valid @RequestBody Ppdb ppdb, @RequestParam(name = "transaction_type", required = false) String transactionType, @RequestParam(name = "transaction_status", required = false) String transactionStatus) {
        return ppdbService.newPpdb(ppdb);
    }

    @PutMapping("/update/{id}")
    public Ppdb updatePpdb(@PathVariable(value = "id") UUID ppdbId,
                           @Valid @RequestBody Ppdb ppdbDetails, @RequestParam(name = "transaction_type", required = false) String transactionType, @RequestParam(name = "transaction_status", required = false) String transactionStatus) {

        return ppdbService.updatePpdb(ppdbId, ppdbDetails);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deletePpdb(@PathVariable(value = "id") UUID ppdbId) {
        return ppdbService.deletePpdb(ppdbId);
    }


}

package id.ten.springimportcsvpostgres.controller;

import id.ten.springimportcsvpostgres.service.CSVService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/csv")
@AllArgsConstructor
public class CSVController {

    private CSVService csvService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("file")MultipartFile file) {
        try {
            csvService.save(file);
            return ResponseEntity.status(HttpStatus.OK).body("Upload CSV Sukses");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Terjadi kesalahan pada saat upload");
        }
    }

}

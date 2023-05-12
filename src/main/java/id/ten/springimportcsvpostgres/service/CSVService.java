package id.ten.springimportcsvpostgres.service;

import id.ten.springimportcsvpostgres.helpers.CSVHelper;
import id.ten.springimportcsvpostgres.models.Mahasiswa;
import id.ten.springimportcsvpostgres.repository.MahasiswaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class CSVService {

    private MahasiswaRepository mahasiswaRepository;

    public void save(MultipartFile file) {
        try {
            final List<Mahasiswa> mahasiswaList = CSVHelper.transformCSVToList(file.getInputStream());
            mahasiswaRepository.saveAll(mahasiswaList);
        }catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<Mahasiswa> mahasiswaList = mahasiswaRepository.findAll();

        ByteArrayInputStream inputStream = CSVHelper.mahasiswaToCSV(mahasiswaList);

        return inputStream;
    }
}

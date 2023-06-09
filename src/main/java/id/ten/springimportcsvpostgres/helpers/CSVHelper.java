package id.ten.springimportcsvpostgres.helpers;

import id.ten.springimportcsvpostgres.models.Mahasiswa;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {

    public static String TYPE = "text/csv";
    static String[] HEADERs = { "nim", "nama", "email" };

    public static boolean hasCSVFormat(MultipartFile file) {
        final String contentType = file.getContentType();
        if(TYPE.equals(contentType) || file.getContentType().equals("application/vnd.ms-excel")) {
            return true;
        }
        return false;
    }

    public static List<Mahasiswa> transformCSVToList(InputStream inputStream) {
        try(BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            CSVParser csvParser = new CSVParser(fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Mahasiswa> mahasiswaList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for(CSVRecord csvRecord: csvRecords) {
                Mahasiswa mahasiswa = Mahasiswa.builder()
                        .nim(csvRecord.get("nim"))
                        .nama(csvRecord.get("nama"))
                        .email(csvRecord.get("email"))
                        .build();

                mahasiswaList.add(mahasiswa);
            }

            return mahasiswaList;
        }catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file "+ e.getMessage());
        }
    }

    public static ByteArrayInputStream mahasiswaToCSV(List<Mahasiswa> mahasiswaList) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try(ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);)  {

            for(Mahasiswa mahasiswa: mahasiswaList) {
                List<String> data = Arrays.asList(
                        mahasiswa.getNim(),
                        mahasiswa.getNama(),
                        mahasiswa.getEmail()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());

        }catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}

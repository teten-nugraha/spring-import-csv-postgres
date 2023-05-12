package id.ten.springimportcsvpostgres.repository;

import id.ten.springimportcsvpostgres.models.Mahasiswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long> {
}

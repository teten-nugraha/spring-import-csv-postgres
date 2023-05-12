package id.ten.springimportcsvpostgres.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "mahasiswas")
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mahasiswa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idl;

    @Column(name = "nim")
    private String nim;

    @Column(name = "nama")
    private String nama;

    @Column(name = "email")
    private String email;



}

package meli.com.apifut.Repository;

import meli.com.apifut.Model.Time;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM ApiFutebol.time WHERE nome = :nome AND sigla_estado = :siglaEstado")
    Time findTimeDuplicado(@Param("nome") String nome, @Param("siglaEstado") String siglaEstado);

    @Query (nativeQuery = true, value = "SELECT MIN(data_partida) from ApiFutebol.partida where time_casa_id = :id OR time_visitante_id = :id")
    LocalDate findPrimeiraPartida(@Param("id") Long id);

    Page<Time> findAllByNomeContainingAndSiglaEstadoContainingAndStatus(String nome, String siglaEstado, Boolean status, Pageable pageable);
}
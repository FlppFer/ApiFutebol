package meli.com.apifut.Repository;

import meli.com.apifut.Model.Time;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM ApiFutebol.time WHERE id = :id")
    Time findTimeById(@Param("id")Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM ApiFutebol.time WHERE nome = :nome AND sigla_estado = :siglaEstado")
    Time findTimeDuplicado(@Param("nome") String nome, @Param("siglaEstado") String siglaEstado);

    @Query (nativeQuery = true, value = "SELECT MIN(data_hora_partida) FROM ApiFutebol.partida WHERE time_casa_id = :id OR time_visitante_id = :id")
    LocalDateTime findPrimeiraPartidaById(@Param("id") Long id);

    @Query(nativeQuery = true, value ="SELECT * FROM ApiFutebol.time WHERE id = :id AND status = 1")
    Time isTimeAtivo(@Param("id") Long time);

    @Query (nativeQuery = true, value= "SELECT * FROM ApiFutebol.time WHERE nome = :nome OR sigla_estado = :siglaEstado OR status = :status")
    Page<Time> findAllByNomeOrSiglaEstadoORStatus(@Param("nome") String nome, @Param("siglaEstado") String siglaEstado, @Param("status") Boolean status, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT data_criacao FROM ApiFutebol.time WHERE id = :id")
    LocalDateTime findDataCriacaoTime(@Param("id") Long id);

}

package meli.com.apifut.Repository;

import meli.com.apifut.Model.Time;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import meli.com.apifut.Model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;


@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM partida WHERE time_casa_id = :timeId OR time_visitante_id = :timeId")
    Page<Partida> findPartidaByTimeId(@Param("timeId") Long timeId, Pageable pageable);
    @Query (nativeQuery = true, value = "SELECT * FROM partida WHERE estadio_id = :estadioId")
    Page<Partida> findPartidaByEstadioId(@Param("estadioId")Long estadioId, Pageable pageable);
    @Query (nativeQuery = true, value = "SELECT * FROM ApiFutebol.partida WHERE data_hora_partida BETWEEN " +
            "DATE_SUB(:dataHoraPartida, INTERVAL 48 hour) AND DATE_ADD(:dataHoraPartida, INTERVAL 48 hour) AND :timeId IN (time_casa_id, time_visitante_id)")
    Partida findConflitoHorarioPartida(@Param("dataHoraPartida") LocalDateTime dataHoraPartida, @Param("timeId") Long timeId);
    @Query(nativeQuery = true, value = "SELECT * FROM ApiFutebol.partida WHERE DATE(data_hora_partida) = DATE(:dataHoraPartida) AND estadio_id = :id")
    Partida findPartidaByDataAndEstadio (@Param("dataHoraPartida") LocalDateTime dataHoraPartida, @Param("id") Long id);
    Page<Partida> findAllByTimeCasaIdOrTimeVisitanteIdOrEstadioId(Long timeId, Long timeId1, Long estadioId, Pageable pageable);
//    Page<Partida> findAllByTimeContainingAndEstadioContaining(String nome, String siglaEstado, Boolean status, Pageable pageable);

}


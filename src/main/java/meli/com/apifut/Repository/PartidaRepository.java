package meli.com.apifut.Repository;

import meli.com.apifut.Model.HistoricoGeral;
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
    @Query(nativeQuery = true, value = "SELECT * FROM partida WHERE id = :id")
    Partida findPartidaById(@Param("id") Long id);

    @Query (nativeQuery = true, value = "SELECT * FROM ApiFutebol.partida WHERE data_hora_partida BETWEEN " +
            "DATE_SUB(:dataHoraPartida, INTERVAL 48 hour) AND DATE_ADD(:dataHoraPartida, INTERVAL 48 hour) AND :timeId IN (time_casa_id, time_visitante_id)")
    Partida findConflitoHorarioPartida(@Param("dataHoraPartida") LocalDateTime dataHoraPartida, @Param("timeId") Long timeId);

    @Query(nativeQuery = true, value = "SELECT * FROM ApiFutebol.partida WHERE DATE(data_hora_partida) = DATE(:dataHoraPartida) AND estadio_id = :id")
    Partida findPartidaByDataAndEstadio (@Param("dataHoraPartida") LocalDateTime dataHoraPartida, @Param("id") Long id);

    @Query(nativeQuery = true, value ="SELECT * FROM ApiFutebol.partida WHERE time_casa_id = :timeId OR time_visitante_id = :timeId OR  estadio_id = :estadioId")
    Page<Partida> findAllByTimeCasaIdOrTimeVisitanteIdOrEstadioId(@Param("timeId") Long timeId, @Param("estadioId") Long estadioId, Pageable pageable);

    //querys de historico

    @Query (nativeQuery = true, value= "SELECT COUNT(*) FROM partida WHERE (time_casa_id = :timeId AND resultado = 'Vitória do time da casa') OR (time_visitante_id = :timeId AND resultado = 'Vitória do time visitante')\n")
    Long countVitorias(Long timeId);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM partida WHERE resultado = 'Empate' AND (time_casa_id = :timeId OR time_visitante_id = :timeId)")
    Long countEmpates(Long timeId);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM partida WHERE (time_casa_id = :timeId AND resultado = 'Vitória do time visitante') OR (time_visitante_id = :timeId AND resultado = 'Vitória do time da casa')\n")
    Long countDerrotas(Long timeId);

    @Query(nativeQuery = true, value= "SELECT COALESCE(SUM(CASE WHEN time_casa_id = :timeId THEN gols_time_casa ELSE 0 END) + SUM(CASE WHEN time_visitante_id = :timeId THEN gols_time_visitante ELSE 0 END), 0) AS total_goals FROM ApiFutebol.partida")
    Long countGolsFeitos(Long timeId);

    @Query(nativeQuery = true, value = "SELECT COALESCE(SUM(CASE WHEN time_casa_id = :timeId THEN gols_time_visitante ELSE 0 END) + SUM(CASE WHEN time_visitante_id = :timeId THEN gols_time_casa ELSE 0 END), 0) AS total_goals FROM ApiFutebol.partida;")
    Long countGolsSofridos(Long timeId);
}

package meli.com.apifut.Repository;

import meli.com.apifut.Model.Time;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import meli.com.apifut.Model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
    @Query("SELECT p FROM Partida p WHERE p.timeCasa.id = :timeId OR p.timeVisitante.id = :timeId")
    Page<Partida> findPartidaByTimeId(@Param("timeId") Long timeId, Pageable pageable);
    Partida findByTimeCasaIdOrTimeVisitanteId(Long timeId, Pageable pageable);
}


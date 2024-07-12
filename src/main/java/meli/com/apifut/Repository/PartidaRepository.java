package meli.com.apifut.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import meli.com.apifut.Model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
    @Query("SELECT p FROM Partida p WHERE p.timeCasa.id = :timeId OR p.timeVisitante.id = :timeId")
    Page<Partida> findPartidaByTimeId(@Param("timeId") Long timeId, Pageable pageable);
    @Query ("SELECT p FROM Partida p WHERE p.estadio.id = :estadioId")
    Page<Partida> findPartidaByEstadioId(@Param("estadioId")Long estadioId, Pageable pageable);
    @Query ("SELECT p FROM Partida p")
    Page<Partida> findAllPartidas(Pageable pageable);

}


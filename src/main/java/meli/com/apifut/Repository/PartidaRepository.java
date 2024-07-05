package meli.com.apifut.Repository;

import meli.com.apifut.Model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findByClubeCasaId(Long clubeCasaId);
    List<Partida> findByClubeVisitanteId(Long clubeVisitanteId);
    List<Partida> findByEstadioId(Long estadioId);
}

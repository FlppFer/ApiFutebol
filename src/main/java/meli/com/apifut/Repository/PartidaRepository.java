package meli.com.apifut.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import meli.com.apifut.Model.Estadio;
import meli.com.apifut.Model.Partida;
import meli.com.apifut.Model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
    Page<Partida> findByTimeCasaOrTimeVisitanteOrEstadio(Time timeCasa, Time timeVisitante, Estadio estadio , Pageable pageable);
}


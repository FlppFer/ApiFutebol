package meli.com.apifut.Repository;

import meli.com.apifut.Model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {
    List<Time> findByNomeContaining(String nomeDoTime);
    List<Time> findBySiglaEstado(String SiglaDoEstado);
    List<Time> findByAtivo(boolean status);
}

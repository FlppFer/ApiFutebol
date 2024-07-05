package meli.com.apifut.Repository;

import meli.com.apifut.Model.Estadio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadioRepository extends JpaRepository<Estadio, Long> {

}

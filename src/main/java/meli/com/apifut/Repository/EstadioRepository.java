package meli.com.apifut.Repository;

import meli.com.apifut.Model.Estadio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EstadioRepository extends JpaRepository<Estadio, Long> {


}

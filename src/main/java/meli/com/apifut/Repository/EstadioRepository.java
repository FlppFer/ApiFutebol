package meli.com.apifut.Repository;

import meli.com.apifut.Model.Estadio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadioRepository extends JpaRepository<Estadio, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM estadio WHERE id = :id")
    Estadio findEstadioById(@Param("id") Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM estadio WHERE nome = :nome")
    Estadio findEstadioByNome(@Param("nome") String nome);
}

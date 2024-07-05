package meli.com.apifut.Repository;

import meli.com.apifut.Model.Time;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {
    Page<Time> findByNomeContainingIgnoreCaseAndSiglaEstadoIgnoreCaseAndStatus(String nome, String estado, Boolean status, Pageable pageable);
}
package meli.com.apifut.Repository;

import meli.com.apifut.Model.Time;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {
   Page<Time> findByNomeDoTimeContainingIgnoreCaseAndSiglaEstadoIgnoreCaseAndStatus(String nome, String estado, Boolean status, Pageable pageable);

   Page<Time> findByNomeDoTimeContainingIgnoreCaseAndSiglaEstadoIgnoreCase(String nome, String estado, Pageable pageable);

   Page<Time> findByNomeDoTimeContainingIgnoreCaseAndStatus(String nome, Boolean status, Pageable pageable);

   Page<Time> findBySiglaEstadoIgnoreCaseAndStatus(String estado, Boolean status, Pageable pageable);

   Page<Time> findByNomeDoTimeContainingIgnoreCase(String nome, Pageable pageable);

   Page<Time> findBySiglaEstadoIgnoreCase(String estado, Pageable pageable);

   Page<Time> findByStatus(Boolean status, Pageable pageable);
}
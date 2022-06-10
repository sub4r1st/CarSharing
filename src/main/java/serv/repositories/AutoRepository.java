package serv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import serv.models.Auto;

public interface AutoRepository extends JpaRepository<Auto, Integer>{
}
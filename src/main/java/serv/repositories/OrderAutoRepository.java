package serv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import serv.models.OrderAuto;

public interface OrderAutoRepository extends JpaRepository<OrderAuto, Integer> {
}

package tomiks.socketiotest.http.repository;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tomiks.socketiotest.http.model.Order;
import tomiks.socketiotest.http.model.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(value = "SELECT * FROM orders WHERE client_id = :userId ORDER BY order_id DESC, canceled ASC, active DESC;", nativeQuery = true)
	List<Order> findAllByClientOrd(Long userId);
	List<Order> findAllByClient(User userId);
	List<Order> findAllByClientAndCanceledIsFalse(User userId);
	List<Order> findAllByClientAndCanceledIsTrue(User userId);
	List<Order> findAllByCanceledIsFalseAndActiveIsTrueOrderByPriorityAsc(Limit limit);
}

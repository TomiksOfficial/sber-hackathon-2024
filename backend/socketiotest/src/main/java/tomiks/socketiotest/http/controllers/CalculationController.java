package tomiks.socketiotest.http.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomiks.socketiotest.http.algorithms.Algorithms;
import tomiks.socketiotest.http.model.Order;
import tomiks.socketiotest.http.repository.OrderRepository;

import java.util.List;

@RestController
@RequestMapping("/api/calculation")
@RequiredArgsConstructor
public class CalculationController {

	private Integer K = 2;
	private final OrderRepository orderRepository;

	@PostMapping("/get_mileage")
	public ResponseEntity<?> getMileage(@RequestBody List<Order> orderList) {
		if (orderList == null || orderList.isEmpty()) {
			return ResponseEntity.ok(null);
		}

		return ResponseEntity.ok(Algorithms.getMileage(orderList, orderList.size()));
	}

	@PostMapping("/get_sorted_order_list")
	public ResponseEntity<?> getSortedOrderList(@RequestBody List<Order> orderList) {
		if (orderList == null || orderList.isEmpty()) {
			return ResponseEntity.ok(null);
		}

		Algorithms.sortOrderList(orderList);

		orderRepository.saveAll(orderList);

		return ResponseEntity.ok(orderList);
	}


}

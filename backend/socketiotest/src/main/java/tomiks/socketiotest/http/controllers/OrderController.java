package tomiks.socketiotest.http.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tomiks.socketiotest.http.config.roles.AdminRole;
import tomiks.socketiotest.http.config.roles.UserRole;
import tomiks.socketiotest.http.model.Order;
import tomiks.socketiotest.http.model.User;
import tomiks.socketiotest.http.model.order.OrderRequest;
import tomiks.socketiotest.http.repository.OrderRepository;
import tomiks.socketiotest.http.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
	private final OrderRepository orderRepository;
	private final UserRepository userRepository;

	@GetMapping("/all_orders")
	@UserRole
	public List<Order> getAllOrders() {
		User user = SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User ?
				(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal() : null;

		if (user == null) {
			return null;
		}

		return orderRepository.findAllByClient(user);
	}

	@GetMapping("/all_active_orders")
	@UserRole
	public List<Order> getAllActiveOrders() {
		User user = SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User ?
				(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal() : null;

		if (user == null) {
			return null;
		}

		return orderRepository.findAllByClientAndCanceledIsFalse(user);
	}

	@GetMapping("/all_inactive_orders")
	@UserRole
	public List<Order> getAllInactiveOrders() {
		User user = SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User ?
				(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal() : null;

		if (user == null) {
			return null;
		}

		return orderRepository.findAllByClientAndCanceledIsTrue(user);
	}

	@PostMapping("/create_order")
	@UserRole
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		User user = SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User ?
				(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal() : null;

		if (user == null || order == null) {
			return ResponseEntity.ok(null);
		}

//		try {
//			Order order = new Order(orderRequest.getOrder_name(), orderRequest.getStreet(), orderRequest.getHouse_number(), user);
//		}

		order.setClient(user);

		return ResponseEntity.ok(orderRepository.save(order));
	}

	@PostMapping("/cancel_order")
	@UserRole
	public ResponseEntity<?> cancelOrder(@RequestBody OrderRequest orderRequest) {
		User user = SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User ?
				(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal() : null;

		if (user == null) {
			return ResponseEntity.ok("user is null");
		}

//		User usr = userRepository.findById(orderRequest.getUser_id()).orElse(null);

		Order order = orderRepository.findById(orderRequest.order_id).orElse(null);

		if (order == null) {
			return ResponseEntity.ok("order is null");
		}

		if (!order.getClient().getId().equals(user.getId())) {
			return ResponseEntity.ok("clients not equals");
		}

		order.setCanceled(true);

		return ResponseEntity.ok(orderRepository.save(order));
	}

	@GetMapping("/admin/active_orders")
	@AdminRole
	public List<Order> getAllActiveOrdersByCount(@RequestParam(value = "count", defaultValue = "50") int count) {
		return orderRepository.findAllByCanceledIsFalseAndActiveIsTrueOrderByPriorityAsc(Limit.of(count));
	}

	@PostMapping("/admin/manage_orders")
	@AdminRole
	public List<Order> manageOrdersPriority(@RequestBody List<Order> orders) {

		if (orders == null || orders.isEmpty()) {
			return null;
		}

		return orderRepository.saveAll(orders);
	}

}

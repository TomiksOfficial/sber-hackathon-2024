package tomiks.socketiotest.http.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long order_id;

	@Column(columnDefinition = "VARCHAR(256)", nullable = false)
	public String order_name;

	@Column(columnDefinition = "VARCHAR(256)", nullable = false)
	public String street;

	@Column(nullable = false)
	public Integer house_number;

	public Boolean canceled = false;

	public Boolean active = true;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long priority;

	@ManyToOne
	public User client;

}

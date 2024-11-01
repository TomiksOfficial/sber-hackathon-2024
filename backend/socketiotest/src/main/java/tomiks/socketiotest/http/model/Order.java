package tomiks.socketiotest.http.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Generated;

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

	@Column(insertable = false, columnDefinition = "BIGSERIAL")
	public Long priority;

	@ManyToOne
//	@JsonIgnoreProperties("orders")
//	@JsonIgnore
	public User client;

//	public Order(String order_name, String street, Integer house_number, User client) {
//		this.order_name = order_name;
//		this.street = street;
//		this.house_number = house_number;
//		this.client = client;
//	}
}

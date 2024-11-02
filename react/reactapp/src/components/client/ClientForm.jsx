import { observer } from "mobx-react-lite";
import React, { useEffect, useState } from "react";

import './ClientForm.css';
import axios from "axios";
import { Link } from "react-router-dom";

export const ClientForm = observer(() => {
	const [orders, setOrders] = useState([
		// {
		// 	"order_id": 8,
		// 	"order_name": "a3",
		// 	"street": "V2",
		// 	"house_number": 2,
		// 	"canceled": false,
		// 	"active": true,
		// 	"priority": 8,
		// 	"client": {
		// 		"id": 2,
		// 		"username": "v",
		// 		"email": "v@v",
		// 		"registration_date": "2024-11-01",
		// 		"api_key": "647ee7be-1e42-44a1-ac84-b1b8d2580ca4",
		// 		"last_login_date": null,
		// 		"roles": [
		// 			{
		// 				"id": 1,
		// 				"name": "ROLE_USER",
		// 				"authority": "ROLE_USER"
		// 			}
		// 		],
		// 		"orders": [],
		// 		"enabled": true,
		// 		"accountNonLocked": true,
		// 		"accountNonExpired": true,
		// 		"credentialsNonExpired": true
		// 	}
		// },
		// {
		// 	"order_id": 6,
		// 	"order_name": "a3",
		// 	"street": "H2",
		// 	"house_number": 5,
		// 	"canceled": false,
		// 	"active": true,
		// 	"priority": 7,
		// 	"client": {
		// 		"id": 2,
		// 		"username": "v",
		// 		"email": "v@v",
		// 		"registration_date": "2024-11-01",
		// 		"api_key": "647ee7be-1e42-44a1-ac84-b1b8d2580ca4",
		// 		"last_login_date": null,
		// 		"roles": [
		// 			{
		// 				"id": 1,
		// 				"name": "ROLE_USER",
		// 				"authority": "ROLE_USER"
		// 			}
		// 		],
		// 		"orders": [],
		// 		"enabled": true,
		// 		"accountNonLocked": true,
		// 		"accountNonExpired": true,
		// 		"credentialsNonExpired": true
		// 	}
		// },
		// {
		// 	"order_id": 7,
		// 	"order_name": "a3",
		// 	"street": "V3",
		// 	"house_number": 3,
		// 	"canceled": false,
		// 	"active": true,
		// 	"priority": 6,
		// 	"client": {
		// 		"id": 2,
		// 		"username": "v",
		// 		"email": "v@v",
		// 		"registration_date": "2024-11-01",
		// 		"api_key": "647ee7be-1e42-44a1-ac84-b1b8d2580ca4",
		// 		"last_login_date": null,
		// 		"roles": [
		// 			{
		// 				"id": 1,
		// 				"name": "ROLE_USER",
		// 				"authority": "ROLE_USER"
		// 			}
		// 		],
		// 		"orders": [],
		// 		"enabled": true,
		// 		"accountNonLocked": true,
		// 		"accountNonExpired": true,
		// 		"credentialsNonExpired": true
		// 	}
		// }
	]);

	useEffect(() => {
		(axios.get(import.meta.env.VITE_BACKEND_URL + "/orders/all_orders").then((res) => {
			if (res.data == null)
			{
				console.log("data is null")
				return;
			}

			setOrders(res.data);
		}).catch(e => {
			console.log(e);
		}))
	}, []);

	async function cancel_order(o) {
		axios.post(import.meta.env.VITE_BACKEND_URL + "/orders/cancel_order", o, {
			withCredentials: true,
			credentials: "include"
			// headers: {
			// 	'SameSite': 'None'
			// }
		}).then(res => {
			(axios.get(import.meta.env.VITE_BACKEND_URL + "/orders/all_orders").then((res) => {
				if (res.data == null)
				{
					console.log("data is null")
					return;
				}
	
				setOrders(res.data);
			}).catch(e => {
				console.log(e);
			}));
		}).catch(e => console.log(e));
	}

    return (
		<>
			<div className="container-orders-o">
				<Link to={"/create_order"} className="btn-classic-create">
					Создать заказ
				</Link>
				{orders.map((o, i) => (
					<div key={i} className="row-order">
						<div className="row-address-order">
							{o.street} | {o.house_number}
						</div>
						<div className="row-description">
							{o.order_name}
						</div>
						{/* <div className="row-status"> */}
							{o.active && !o.canceled && <span className="galochka row-status" onClick={() => {
								cancel_order(o)
							}}>🕒</span>}
							{!o.active && !o.canceled && <span className="row-status">✔️</span>}
							{o.canceled && <span className="row-status">❌</span>}
						{/* </div> */}
					</div>
				))}
			</div>
		</>
	);
});

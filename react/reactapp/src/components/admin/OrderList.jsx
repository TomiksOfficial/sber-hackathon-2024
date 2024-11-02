import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import './AdminForm.css';

export const OrderList = () => {
	const [orders, setOrders] = useState([]);
	const [dist, setDist] = useState(0);

	useEffect(() => {
		(axios.get(import.meta.env.VITE_BACKEND_URL + "/orders/admin/active_orders").then((res) => {
			if (res.data == null)
			{
				console.log("data is null")
				return;
			}

			setOrders(res.data);

			axios.post(import.meta.env.VITE_BACKEND_URL + "/api/calculation/get_mileage", res.data).then(r => {
				if (r.data == null)
				{
					setDist(-1);
				}

				setDist(r.data);
			});

			
		}).catch(e => {
			console.log(e);
		}))
	}, []);

	function doUp(o, ind) {
		if (ind >= orders.length) {
			return;
		}

		var temp = orders[ind-1];
		var pr = temp.priority;
		temp.priority = orders[ind].priority;
		orders[ind].priority = pr;

		orders[ind-1] = orders[ind];
		orders[ind] = temp;

		setOrders([...orders]);

		axios.post(import.meta.env.VITE_BACKEND_URL + "/orders/admin/manage_orders", [...orders]).then(r => {
			axios.post(import.meta.env.VITE_BACKEND_URL + "/api/calculation/get_mileage", [...orders]).then(r => {
				if (r.data == null)
				{
					setDist(-1);
				}

				setDist(r.data);
			});
		}).catch(e => console.log(e));
	}

	function doDown(o, ind) {
		if (ind < 0 || ind+1 >= orders.length) {
			return;
		}

		var temp = orders[ind+1];
		var pr = temp.priority;
		temp.priority = orders[ind].priority;
		orders[ind].priority = pr;

		orders[ind+1] = orders[ind];
		orders[ind] = temp;

		setOrders([...orders]);

		axios.post(import.meta.env.VITE_BACKEND_URL + "/orders/admin/manage_orders", [...orders]).then(r => {
			axios.post(import.meta.env.VITE_BACKEND_URL + "/api/calculation/get_mileage", [...orders]).then(r => {
				if (r.data == null)
				{
					setDist(-1);
				}

				setDist(r.data);
			});
		}).catch(e => console.log(e));
	}

	function optimize() {
		console.log('click')
		axios.post(import.meta.env.VITE_BACKEND_URL + "/api/calculation/get_sorted_order_list", orders).then(r => {
			if (r.data == null) {
				console.log("r null")
				return;
			}

			(axios.get(import.meta.env.VITE_BACKEND_URL + "/orders/admin/active_orders").then((res) => {
				if (res.data == null)
				{
					console.log("data is null")
					return;
				}
	
				setOrders(res.data);
	
				axios.post(import.meta.env.VITE_BACKEND_URL + "/api/calculation/get_mileage", res.data).then(r => {
					if (r.data == null)
					{
						setDist(-1);
					}
	
					setDist(r.data);
				});
	
				
			}).catch(e => {
				console.log(e);
			}))
		}).catch(e => console.log(e));
	}

    return (
		<>
			<div className="container-orders-o">
				<div className="btn-classic-create">
					{dist}
				</div>
				<div className="btn-classic-create" onClick={optimize}>
					Оптимизировать
				</div>
				{orders.map((o, i) => (
					<div key={i} className="row-order">
						<div className="row-address-order">
							{o.street} | {o.house_number}
						</div>
						<div className="row-description">
							{o.order_name}
						</div>
						<div className="orderlist_arrows">
							<span style={{fontSize: "36px", margin: "10px"}} onClick={() => doUp(o, i)}>🠕</span>
							<span style={{fontSize: "36px", margin: "10px"}} onClick={() => doDown(o, i)}>🠗</span>
						</div>
					</div>
				))}
			</div>
		</>
	);
};

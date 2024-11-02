import axios from "axios";
import React, { useEffect, useState } from "react";

export const CurrentOrders = () => {
	const [orders, setOrders] = useState([]);

	useEffect(() => {
		(axios.get(import.meta.env.VITE_BACKEND_URL + "/orders/admin/active_orders").then((res) => {
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

    return (
        <>
            <div className="container-orders-o">
                {orders.map((o, i) => (
                    <div key={i} className="row-order">
                        <div className="row-address-order">
                            {o.street} | {o.house_number}
                        </div>
                        <div className="row-description">{o.order_name}</div>
                    </div>
                ))}
            </div>
        </>
    );
};

import { useState } from "react";

import './OrderForm.css';
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

export const OrderForm = () => {

	const navigate = useNavigate();

	const [desc, setDesc] = useState('');
	const [street, setStreet] = useState('');
	const [hnum, setHNUM] = useState('');

	function create_order() {
		const reg = /[HV]\d+/

		if (!reg.test(street))
		{
			return
		}

		axios.post(import.meta.env.VITE_BACKEND_URL + "/orders/create_order", {
			"order_name": desc,
			"street": street,
			"house_number": hnum
		}, {
			withCredentials: true,
			credentials: "include"
		}).then(r => {
			if (r.status != 200)
			{
				console.log("error create");
				return;
			}

			navigate("/client");
		}).catch(e => console.log(e));
	}

    return (
		<div className="modal-form-o">
			<input type="text" placeholder="Description" value={desc} onChange={e => setDesc(e.target.value)} />
			<input type="text" placeholder="STREET" value={street} onChange={e => {
				
					setStreet(e.target.value);
			}} />
			<input type="text" placeholder="HOUSE NUMBER" value={hnum} onChange={e => setHNUM(e.target.value)} />
			<div className="btn-group-auth-o">
				<button className="btn-auth-o" onClick={create_order}>
					{/* <Link to={"/register"}>Зарегистрироваться</Link> */}
					Добавить
				</button>
			</div>
		</div>
	);
};

import { useState } from "react";

import './RegisterForm.css';
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

export const RegisterForm = () => {

	const [login, setLogin] = useState('');
	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');
	const navigate = useNavigate();

	function reg() {
		if (login.length == 0 || email.length == 0 || password.length == 0) {
			return;
		}

		axios.post(import.meta.env.VITE_BACKEND_URL + "/api/register", {
			"username": login,
			"email": email,
			"password": password
		}).then(r => {
			if (r.status != 200) {
				return;
			}

			navigate("/login");
		}).catch(e => console.log(e));
	}

    return (
		<div className="modal-form">
			<input type="text" placeholder="LOGIN" value={login} onChange={e => setLogin(e.target.value)} />
			<input type="email" placeholder="EMAIL" value={email} onChange={e => setEmail(e.target.value)} />
			<input type="password" placeholder="PASSWORD" value={password} onChange={e => setPassword(e.target.value)} />
			<div className="btn-group-auth">
				<button className="btn-auth" onClick={reg}>
					{/* <Link to={"/register"}>Зарегистрироваться</Link> */}
					Зарегистрироваться
				</button>
			</div>
		</div>
	);
};

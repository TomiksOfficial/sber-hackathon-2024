import { useState } from "react";

import './AuthForm.css';
import { Link } from "react-router-dom";
import { useStore } from "../../store/StoreContext";

export const AuthForm = () => {

	const { authStore } = useStore();

	const [login, setLogin] = useState('');
	const [password, setPassword] = useState('');

	function auth() {
		if (login.length == 0 || password.length == 0)
		{
			return;
		}

		authStore.auth(login, password);
	}

    return (
		<div className="modal-form">
			<input type="text" placeholder="LOGIN/EMAIL" onChange={(e) => { setLogin(e.target.value); }} value={login} />
			<input type="password" placeholder="PASSWORD" onChange={(e) => { setPassword(e.target.value); }} value={password} />
			<div className="btn-group-auth">
				<button className="btn-auth">
					<Link to={"/register"} className="link">Зарегистрироваться</Link>
				</button>
				<button className="btn-auth" onClick={() => {
					auth();
				}}>Авторизоваться</button>
			</div>
		</div>
	);
};

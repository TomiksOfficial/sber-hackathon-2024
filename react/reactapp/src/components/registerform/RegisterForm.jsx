import { useState } from "react";

import './RegisterForm.css';
import { Link } from "react-router-dom";

export const RegisterForm = () => {

	const [login, setLogin] = useState('');
	const [password, setPassword] = useState('');

    return (
		<div className="modal-form">
			<input type="text" placeholder="LOGIN" />
			<input type="text" placeholder="EMAIL" />
			<input type="text" placeholder="PASSWORD" />
			<div className="btn-group-auth">
				<button className="btn-auth">
					{/* <Link to={"/register"}>Зарегистрироваться</Link> */}
				</button>
			</div>
		</div>
	);
};

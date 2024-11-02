import { observer } from "mobx-react-lite";
import "./Navbar.css";
import { useStore } from "../../store/StoreContext";
import { AuthForm } from "../authform/AuthForm";
import { useState } from "react";
import { Link, useNavigate } from 'react-router-dom';

export const Navbar = observer(() => {

	const { authStore } = useStore();
	const navigate = useNavigate();

	function logout() {
		if (authStore.logout())
		{
			navigate("/login");
		}
	}

    return (
		<>
			<div className="navbar">
				{
					!authStore.isAuth ?
					<Link to={"/login"} className="link btn-classic">
						Авторизация
					</Link>
					:
					<button onClick={logout} className="btn-classic">
						Выйти
					</button>
				}
			</div>
		</>
    );
});

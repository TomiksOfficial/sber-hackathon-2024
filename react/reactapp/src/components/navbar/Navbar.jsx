import { observer } from "mobx-react-lite";
import "./Navbar.css";
import { useStore } from "../../store/StoreContext";
import { AuthForm } from "../authform/AuthForm";
import { useState } from "react";
import { Link } from 'react-router-dom';

export const Navbar = observer(() => {

	const { authStore } = useStore();

    return (
		<>
			<div className="navbar">
				{
					!authStore.isAuth ?
					<Link to={"/login"} className="link btn-classic">
						Авторизация
					</Link>
					:
					<button className="btn-classic">
						Выйти
					</button>
				}
			</div>
		</>
    );
});

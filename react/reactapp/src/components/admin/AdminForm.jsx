import { observer } from "mobx-react-lite";
import React, { useEffect, useState } from "react";

import './AdminForm.css';
import axios from "axios";
import { Link } from "react-router-dom";

export const AdminForm = observer(() => {

    return (
		<>
			<div className="container-orders-o">
				<Link to={"/all_orders"} className="btn-classic-create">
					Смотреть заказы
				</Link>
				<Link to={"/manage_orders"} className="btn-classic-create">
					Редактирование приоритетов
				</Link>
			</div>
		</>
	);
});

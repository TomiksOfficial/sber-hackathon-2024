import "./App.css";
import { AuthForm } from "./components/authform/AuthForm";
import { Navbar } from "./components/navbar/navbar";
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';
import { RegisterForm } from "./components/registerform/RegisterForm";
import axios from "axios";
import { useStore } from "./store/StoreContext";
import { ClientForm } from "./components/client/ClientForm";
import { AdminForm } from "./components/admin/AdminForm";
import { observer } from "mobx-react-lite";
import { OrderForm } from "./components/OrderForm/OrderForm";
import { OrderList } from "./components/admin/OrderList";
import { CurrentOrders } from "./components/admin/CurrentOrders";

const App = observer(() => {
	axios.defaults.withCredentials = true;

	const { authStore } = useStore();

    return (
        <>
            {/* <div> */}
				<BrowserRouter>
					<Navbar/>
					{/* <div> */}
						<div className='content'>
								<Routes>
									{!authStore.isAuth && <Route path="/login" element={<AuthForm/>} />}
									{!authStore.isAuth && <Route path="/register" element={<RegisterForm />} />}
									{authStore.isAuth && <Route path="/client" element={<ClientForm />}></Route>}
									{authStore.isAuth && <Route path="/create_order" element={<OrderForm />}></Route>}
									{authStore.isAuth && authStore.client.roles.some(o => o.name == "ROLE_ADMIN") && <Route path="/manage_orders" element={<OrderList />}></Route>}
									{authStore.isAuth && authStore.client.roles.some(o => o.name == "ROLE_ADMIN") && <Route path="/all_orders" element={<CurrentOrders />}></Route>}
									{authStore.isAuth && authStore.client.roles.some(o => o.name == "ROLE_ADMIN") && <Route path="/admin" element={<AdminForm />}></Route>}
									{/* <Route path="/NotFound" element={<NotFoundModule/>} /> */}
									<Route path="*" element={<Navigate to="/NotFound" replace />} />
								</Routes>
						</div>
					{/* </div> */}
				</BrowserRouter>
            {/* </div> */}
        </>
    );
});

export default App;
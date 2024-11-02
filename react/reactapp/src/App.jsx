import "./App.css";
import { AuthForm } from "./components/authform/AuthForm";
import { Navbar } from "./components/navbar/navbar";
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';
import { RegisterForm } from "./components/registerform/RegisterForm";
import axios from "axios";
import { useStore } from "./store/StoreContext";

function App() {
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
									<Route path="/client" element={<ClientForm />}></Route>
									{/* <Route path="/NotFound" element={<NotFoundModule/>} /> */}
									<Route path="*" element={<Navigate to="/NotFound" replace />} />
								</Routes>
						</div>
					{/* </div> */}
				</BrowserRouter>
            {/* </div> */}
        </>
    );
};

export default App;
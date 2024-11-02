import axios from "axios";
import { makeAutoObservable, runInAction } from "mobx";

class AuthStore {
    isAuth = false;

	client = {};

    constructor() {
        makeAutoObservable(this);
    }

    setIsAuth = (b) => {
        this.isAuth = b;
    };

	auth = async (username, password) => {
		if (this.isAuth) {
			return true;
		}

		console.log(import.meta.env.VITE_BACKEND_URL + "/api/login");

		let result = (await axios.post(import.meta.env.VITE_BACKEND_URL + "/api/login", {
			"login": username,
			"password": password
		}, {
			withCredentials: true,
			credentials: "include"
			// headers: {
			// 	'SameSite': 'None'
			// }
		}).then((result) => {
			if (result.status != 200)
			{
				return false;
			}
	
			runInAction(() => {
				this.client = result.data;
				this.isAuth = true;
			});

			return true;
		}).catch(e => {
			console.log("err " + e);
			return false;
		}));
	}

	logout = async () => {
		if (!this.isAuth) {
			return true;
		}

		let result = (await axios.post(import.meta.env.VITE_BACKEND_URL + "/api/logout", undefined, {
			withCredentials: true,
			credentials: "include"
			// headers: {
			// 	'SameSite': 'None'
			// }
		}).then((result) => {
			if (result.status != 200)
			{
				return false;
			}
	
			runInAction(() => {
				this.client = {};
				this.isAuth = false;
			});

			return true;
		}).catch(e => {
			console.log(e);
			return false;
		}));
	}
}

export default new AuthStore();

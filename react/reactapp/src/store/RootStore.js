import AuthStore from "./AuthStore";
import DataStore from "./DataStore";

class RootStore {
    dstore = DataStore;

	authStore = AuthStore;
}

export default RootStore;

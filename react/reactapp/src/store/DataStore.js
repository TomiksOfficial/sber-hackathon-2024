import { makeAutoObservable } from "mobx";

class DataStore {
    counter = 0;

    constructor() {
        makeAutoObservable(this);
    }

    add = (n) => {
        this.counter += n;
    };
}

export default new DataStore();

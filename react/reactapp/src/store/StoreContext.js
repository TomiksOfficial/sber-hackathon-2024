import { createContext, useContext } from "react";

export const StoreContext = createContext(null);

export const useStore = () => {
    const context = useContext(StoreContext);

    if (context === null) {
        throw new Error("Context null");
    }

    return context;
};

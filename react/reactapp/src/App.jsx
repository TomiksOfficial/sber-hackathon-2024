import { observer } from "mobx-react-lite";
import "./App.css";
import { useStore } from "./store/StoreContext";

const APP = observer(() => {
    const { dstore } = useStore();

    return (
        <>
            <div
                onClick={() => {
                    dstore.add(1);
                }}
            >
                Test: {dstore.counter}
				<br />
				<div className="iobutton"></div>
            </div>
        </>
    );
});

export default APP;
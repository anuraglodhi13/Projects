import {createStore} from "redux";
import rootReducers from "./reducers/index";

const persistentState = localStorage.getItem('state') ? JSON.parse(localStorage.getItem('state')) : {}
const store = createStore(rootReducers,persistentState);
store.subscribe(() => {
    const state = store.getState();
    const serializedState = JSON.stringify(state);
    localStorage.setItem('state', serializedState);
  })
export default store;
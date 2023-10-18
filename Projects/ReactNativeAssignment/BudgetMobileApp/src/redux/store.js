import { createStore, applyMiddleware} from 'redux';
import { budgetReducer } from '../redux/reducer';
import thunk from "redux-thunk"
const configureStore = () => {
    return createStore(budgetReducer, applyMiddleware(thunk));
}
export default configureStore;
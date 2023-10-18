import React from 'react';
import ReactDOM from 'react-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import App from './components/App';
import NewPost from './components/NewPost';
import { BrowserRouter,Route,Routes  } from 'react-router-dom';
import store from './store';
import {Provider} from 'react-redux';
import CurrentPost from './components/CurrentPost';
import EditPost from './components/EditPost';
ReactDOM.render(
 
    <Provider store = {store}> 
<BrowserRouter >
    <div>
        <Routes>
          <Route path="/edit/:id" element={<EditPost />} />
          <Route path=":id" element={<CurrentPost />} />
          <Route path="new" element={<NewPost />} />
          <Route path="/" element={<App />}>
        </Route>
        </Routes>
    </div>
  </BrowserRouter>
  </Provider>, 
document.getElementById("root"));
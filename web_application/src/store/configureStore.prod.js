import { createStore, applyMiddleware } from 'redux';
import rootReducer from '../reducers';
import {routerMiddleware} from 'react-router-redux';
import {browserHistory} from 'react-router';
import thunkMiddleware from 'redux-thunk';

const middleware = routerMiddleware(browserHistory);

export default function configureStore(initialState) {
  return createStore(rootReducer, initialState, applyMiddleware(middleware, thunkMiddleware));
}

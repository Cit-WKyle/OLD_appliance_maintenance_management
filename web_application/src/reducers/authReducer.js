import {initialState} from './initialState';
import {LOGIN_FAILURE, LOGIN_SUCCESS, LOGIN_REQUEST, LOGOUT_REQUEST, LOGOUT_SUCCESS} from '../constants/actionTypes';

export default function auth(state = initialState.auth, action) {

  switch(action.type) {
    case(LOGIN_REQUEST):
          return Object.assign({}, state, {isFetching: action.isFetching, isAuthenticated: action.isAuthenticated, user: action.accountDetails});
    case(LOGIN_SUCCESS):
      return Object.assign({}, state, {isFetching: action.isFetching, isAuthenticated: action.isAuthenticated, errorMessage: ''});
    case(LOGIN_FAILURE):
      return Object.assign({}, state, {isFetching: action.isFetching, isAuthenticated: action.isAuthenticated, errorMessage: action.message});
    case(LOGOUT_REQUEST):
      return Object.assign({}, state, {isFetching: action.isFetching, isAuthenticated: action.isAuthenticated});
    case(LOGOUT_SUCCESS):
      return Object.assign({}, state, {isFetching: action.isFetching, isAuthenticated: action.isAuthenticated});
    default:
          return state;
  }
}

import {LOGOUT_REQUEST, LOGOUT_SUCCESS} from '../constants/actionTypes';
import {ID_TOKEN_KEY} from '../constants/auth';
import {push} from 'react-router-redux';

function requestLogout() {
  return {
    type: LOGOUT_REQUEST,
    isFetching: true,
    isAuthenticated: true
  };
}

function receiveLogout() {
  return {
    type: LOGOUT_SUCCESS,
    isFetching: false,
    isAuthenticated: false
  };
}

export function logoutUser() {
  return dispatch => {
    dispatch(requestLogout());
    localStorage.removeItem(ID_TOKEN_KEY);
    dispatch(receiveLogout());
    dispatch(push('/'));
  };
}

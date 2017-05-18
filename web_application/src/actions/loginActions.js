import {LOGIN_REQUEST, LOGIN_SUCCESS, LOGIN_FAILURE} from '../constants/actionTypes';
import {LOGIN_URL, POST_REQUEST, JSON_CONTENT, JSON_DATA} from '../constants/server';
import {ID_TOKEN_KEY} from '../constants/auth';
import {push} from 'react-router-redux';
import $ from 'jquery';

function requestLogin(accountDetails) {
  return {
    type: LOGIN_REQUEST,
    isFetching: true,
    isAuthenticated: false,
    accountDetails
  };
}

function receiveLogin(user) {
   return {
     type: LOGIN_SUCCESS,
     isFetching: false,
     isAuthenticated: true,
     token: user.token
   };
}

function loginError(message) {
  return {
    type: LOGIN_FAILURE,
    isFetching: false,
    isAuthenticated: false,
    message
  };
}
//Change this function to match server implementation
export function loginUser(accountDetails) {

  return dispatch => {
    dispatch(requestLogin(accountDetails));

    let promise =  $.ajax({
      type: POST_REQUEST,
      contentType: JSON_CONTENT,
      dataType: JSON_DATA,
      url: LOGIN_URL,
      data: JSON.stringify({username: accountDetails.email, password: accountDetails.password})
    });
    promise.done(function(response) {
      //console.log(response);
      localStorage.setItem(ID_TOKEN_KEY, response.token);
      dispatch(receiveLogin(response));
      dispatch(push('/dashboard'));
    });
    promise.fail(function(response) {
      //console.log(response);
      let json = JSON.parse(response.responseText);
      dispatch(loginError(json.message));
    });
    return promise;
  };
}

import {ID_TOKEN_KEY} from '../constants/auth';

export const initialState = {
  auth: {
    isFetching: false,
    isAuthenticated: localStorage.getItem(ID_TOKEN_KEY) ? true : false
  }
};

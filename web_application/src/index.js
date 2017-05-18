import React from 'react';
import configureStore from './store/configureStore';
import ReactDOM from 'react-dom';
import {Provider} from 'react-redux';
import {Router, browserHistory} from 'react-router';
import {routes} from './routes';
import {access_roles} from './constants/auth';
import {initialState} from './reducers/initialState';

const store = configureStore(initialState);

function requireAccess(role, nextState, replace) {
  switch (role) {
    case(access_roles.anon):
      if (store.getState().auth.isAuthenticated) {
        replace('/dashboard');
      }
      break;
    case(access_roles.public):
      return;
    case(access_roles.user):
      if (!store.getState().auth.isAuthenticated) {
        replace('/login');
      }
  }
}

ReactDOM.render(
    <Provider store={store}>
      <Router history={browserHistory} routes={routes(requireAccess)}/>
    </Provider>, document.getElementById('app')
);

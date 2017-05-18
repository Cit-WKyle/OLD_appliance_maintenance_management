import React from 'react';
import {Route, IndexRoute} from 'react-router';
import AppContainer from './containers/AppContainer';
import HomePage from './components/HomePage';
import LoginPageContainer from './containers/LoginPageContainer';
import DashboardPage from './components/DashboardPage';

import {access_roles} from './constants/auth';


export const routes = (requireAccess) => {
  return (
    <Route path="/" component={AppContainer}>
      <IndexRoute component={HomePage} onEnter={requireAccess.bind(this, access_roles.public)}/>
      {" "}
      <Route path="/login" component={LoginPageContainer} onEnter={requireAccess.bind(this, access_roles.anon)}/>
      <Route path="/dashboard" component={DashboardPage} onEnter={requireAccess.bind(this, access_roles.user)}/>
    </Route>
  );
};

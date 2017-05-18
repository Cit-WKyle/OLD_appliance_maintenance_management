import {connect} from 'react-redux';
import App from '../components/App';
import {logoutUser} from '../actions/logoutActions';

function mapStateToProps(state) {
  const isAuthenticated = state.auth.isAuthenticated;
  const errorMessage = state.auth.errorMessage;
  return {
    isAuthenticated,
    errorMessage
  };
}

function mapDispatchToProps(dispatch) {
  return {
    logout: () => {
      dispatch(logoutUser());
    }
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(App);

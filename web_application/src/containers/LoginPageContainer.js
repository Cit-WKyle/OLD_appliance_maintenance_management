import {loginUser} from '../actions/loginActions';
import {connect} from 'react-redux';
import LoginPage from '../components/LoginPage';

//pass in state
function mapStateToProps() {
  return {

  };
}

function mapDispatchToProps(dispatch) {
  return {
    login: (accountDetails) => {
      dispatch(loginUser(accountDetails));
    }
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(LoginPage);

import React from 'react';

const EMAIL_PLACEHOLDER = "Email:";
const PASSWORD_PLACEHOLDER = "Password:";

class LoginPage extends React.Component {
    constructor(props) {
      super(props);
      this.submitLogin = this.submitLogin.bind(this);
    }

    submitLogin(event) {
      event.preventDefault();
      let accountDetails = {
        email: event.target.email.value,
        password: event.target.password.value
      };

      this.props.login(accountDetails);
    }

    render() {
      return (
        <div>
          <h1>Login Page:</h1>
          <form onSubmit={this.submitLogin}>
            <input type="email" name="email" placeholder={EMAIL_PLACEHOLDER}/>
            <br></br>
            <br></br>
            <input type="password" name="password" placeholder={PASSWORD_PLACEHOLDER}/>
            <br></br>
            <br></br>
            <input type="submit" />
          </form>
        </div>
      );
    }
}

LoginPage.propTypes = {
  login: React.PropTypes.func.isRequired
};

export default LoginPage;

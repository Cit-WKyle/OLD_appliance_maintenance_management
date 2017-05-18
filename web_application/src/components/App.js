import React from 'react';
import {Link, IndexLink} from 'react-router';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.renderAuthItems = this.renderAuthItems.bind(this);
  }

  renderAuthItems() {
    if (this.props.isAuthenticated) {
      return (
        <span>
          <Link to="/dashboard">Dashboard</Link>
          {" | "}
          <a href="#" onClick={this.props.logout}>Logout</a>
        </span>
      );
    } else {
      return (
        <Link to="/login">Login</Link>
      );
    }
  }

  render() {
    return (
      <div>
        <IndexLink to="/">Home</IndexLink>
        {" | "}
        {this.renderAuthItems()}
        <br></br>
        <br></br>
        <div>
          Error Messages:
          {this.props.errorMessage}
        </div>
        <br></br>
        {this.props.children}
      </div>
    );
  }
}

App.propTypes = {
  children: React.PropTypes.element,
  isAuthenticated: React.PropTypes.bool.isRequired,
  errorMessage: React.PropTypes.string,
  logout: React.PropTypes.func.isRequired
};

export default App;

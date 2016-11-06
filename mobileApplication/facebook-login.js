/**
 * Created by Adina on 11/4/2016.
 */
import React, { PropTypes, Component } from 'react';
//var {FBLogin, FBLoginManager} = require('react-native-facebook-login');
import {FBLogin, FBLoginManager} from 'react-native-facebook-login';

class FacebookLogin extends Component {
    render() {
        return (
            <FBLogin />
    );
    }
};
export default FacebookLogin;
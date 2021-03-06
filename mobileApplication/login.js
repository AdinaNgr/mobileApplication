/**
 * Created by Adina on 11/4/2016.
 */

'use strict';
import React, {Component} from 'react';
import {
    Navigator,
    StatusBar,
    TouchableHighlight,
    AppRegistry,
    StyleSheet,
    Text,
    View,
    Image,
    TextInput,
    ActivityIndicator
} from 'react-native';
import * as firebase from "firebase";
import Button from 'react-native-button'

export default class Login extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            showProgress: false
        }
    }
    async onLoginPressed(){
        console.log("Loggin In");
        this.setState({showProgress: true});
         try 
         {
            await firebase.auth()
                .signInWithEmailAndPassword(this.props.username, this.props.password).then(this.props.navigator.push({index:0}));
                console.log("Logged In!");
         } 
        catch (error) {
            switch(error.code){

                case "EMAIL_TAKEN":
                    alert("The new user account cannot be created because the email is already in use.");
                break;

                case "INVALID_EMAIL":
                    alert("The specified email is not a valid email.");
                break;

                default:
                    alert("Error creating user:");
                }
            console.log(error.toString())
        }
        // Navigate to the Home page, the user is auto logged in

   
    }
    render(){
        return(
                <View style={styles.container}>
                    <Image style={styles.logo}
                         source={{uri: 'http://www.pomepos.com/wp-content/uploads/2015/01/LoginRed.jpg'}}/>
                    <Text style={styles.header}>
                        Login
                     </Text>
                      <TextInput
                        onChangeText={(text)=>this.setState({username: text})}
                        style={styles.input}
                        placeholder="Username"/>
                    <TextInput
                        secureTextEntry={true}
                        onChangeText={(text)=>this.setState({password: text})}
                        style={styles.input}
                        placeholder="Password"/>
                    <TouchableHighlight
                        onPress={() => this.onLoginPressed()}
                        style={styles.button}>
                        <Text style={styles.buttonText}>
                            Log in
                        </Text>
                    </TouchableHighlight>
                 
                     <ActivityIndicator
                         animating={this.state.showProgress}
                         size="large"
                         style={styles.loader}/>
                </View>
            );

    }

}
var styles = StyleSheet.create({
    container:{
       backgroundColor:'white',
        flex: 1,
        paddingTop: 80,
        paddingRight:20,
        alignItems: 'center'
    },
    logo:{
        width: 80,
        height: 66
    },
    header:{
        fontSize: 30,
        marginTop: 8,
        marginBottom: 30
    },
    input:{
        height: 50,
        width: 250,
        marginTop: 10,
        padding: 4,
        fontSize: 18,
        borderWidth: 1,
        borderColor: '#48bbec'
    },
    button:{
        height: 50,
        width: 250,
        marginTop: 50,
        marginLeft: 40,
        backgroundColor: '#48BBEC',
        alignSelf: 'stretch',
        justifyContent: 'center'
    },
    buttonText:{
        fontSize: 20,
        color: '#FFF',
        alignSelf: 'center'
    },
    loader: {
        marginTop: 20
    }
});

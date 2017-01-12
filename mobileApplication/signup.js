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

export default class SignUp extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            showProgress: false
        }
    }
    async onLoginPressed(){
        console.log("Account creation");
        this.setState({showProgress: true});
         try {
        await firebase.auth()
            .createUserWithEmailAndPassword(this.state.username, this.state.password).then(this.props.navigator.push({index:0}));

        console.log("Account created");

        // Navigate to the Home page, the user is auto logged in

    } catch (error) {
        console.log(error.toString())
    }
    }
    render(){
        return(
                <View style={styles.container}>
                    <Image style={styles.logo}
                         source={{uri: 'http://www.pomepos.com/wp-content/uploads/2015/01/LoginRed.jpg'}}/>
                    <Text style={styles.header}>
                        Sign Up
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
                            Sign Up
                        </Text>
                    </TouchableHighlight>
                    <Button
                        style={{fontSize: 20, color: 'blue', paddingTop: 30}}
                        onPress={()=> this.props.navigator.push({index:7})}>
                        Already have an account? Sign In
                    </Button>
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

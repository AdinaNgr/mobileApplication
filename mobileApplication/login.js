/**
 * Created by Adina on 11/4/2016.
 */
/*
'use strict';
import React from 'react';
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
class Login extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            showProgress: false
        }
    }
    onLoginPressed(){
        this.setState({showProgress: true});
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

export default Login;*/
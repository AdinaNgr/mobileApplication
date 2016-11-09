/**
 * Created by Adina on 11/8/2016.
 */
'use strict';

import React from 'react';

import {
    AppRegistry,
    StyleSheet,
    Text,
    View,
    TouchableOpacity,
    TextInput
} from 'react-native';
import Button from 'react-native-button';

// either import the whole module and call as Communications.method()
import Communications from 'react-native-communications';

// or can now import single methods and call straight via the method name
// import { web, phonecall } from 'react-native-communications';
// e.g. onPress={() => { web('http://www.github.com') }}

class GmailForm extends React.Component{

    constructor(props){
        super(props);
        this.state={
           recieverEmail: 'Ana'
        }
    }
    render() {
        return (
            <View style={styles.container}>
                <TextInput
                    onChangeText={(text)=>this.setState({recieverEmail: text})}
                    style={styles.reciever}
                    placeholder="Email to"/>
                <TextInput
                    onChangeText={(text)=>this.setState({subject: text})}
                    style={styles.input}
                    placeholder="Subject"/>
                <TextInput
                    onChangeText={(text)=>this.setState({body: text})}
                    style={styles.body}
                    placeholder="Message"/>
                <Button style={styles.sendButton}
                      styleDisabled={{color: 'red'}}
                      onPress={() => Communications.email(this.state.recieverEmail,null,null,this.state.subject,this.state.body)}>
                      Send
                </Button>
        </View>
    );
    }
};

var styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        backgroundColor: 'rgb(253,253,253)',
    },
    holder: {
        flex: 0.25,
        justifyContent: 'center',
    },
    text: {
        fontSize: 32,
    },reciever:{
        height: 50,
        width: 250,
        marginTop: 70,
        padding: 4,
        fontSize: 18,
        borderWidth: 1,
        borderColor: '#48bbec'
    },
    input:{
        height: 50,
        width: 250,
        marginTop: 20,
        padding: 4,
        fontSize: 18,
        borderWidth: 1,
        borderColor: '#48bbec'
    },
    body:{
        height: 50,
        width: 250,
        marginTop: 20,
        padding: 4,
        fontSize: 18,
        borderWidth: 1,
        borderColor: '#48bbec'
    },
    sendButton:{
        fontSize: 20,
        color: 'black',
        backgroundColor: '#48BBEC',
        marginTop: 40,
        width: 250,
    }
});

export default GmailForm;
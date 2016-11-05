/**
 * Created by Adina on 11/4/2016.
 */
import React, {Component} from 'react';
import {
    TouchableHighlight,
    Image,
    AppRegistry,
    StyleSheet,
    Text,
    View
} from 'react-native';

class DetailScreen extends React.Component{

    constructor(props){
        super(props);
        this.state={}
    }

    render(){
        return(
            <View style={styles.container}>
                <Text> Title: {this.props.title} </Text>
                <Text> Year: {this.props.year} </Text>
            </View>
        )
    }
}

var styles = StyleSheet.create({
    container:{
        flex: 1,
        padding: 10,
        paddingTop: 70
    }
});

export default DetailScreen;
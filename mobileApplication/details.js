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
    View,
    TextInput
} from 'react-native';

class DetailScreen extends React.Component{

    constructor(props){
        super(props);
        this.state={
            title: this.props.title,
            year: this.props.year
        }
    }

    render(){
        return(
            <View style={styles.container}>
                <Text style={styles.description}> Title: </Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(title) => this.setState({title})}
                    value={this.state.title}
                />
                <Text style={styles.description}> Release Year: </Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(year) => this.setState({year})}
                    value={this.state.year}
                />


            </View>
        )
    }
}

var styles = StyleSheet.create({
    container:{
        flex: 1,
        padding: 10,
        paddingTop: 70
    },
    description:{
        color: 'darkgrey'
    }
});

export default DetailScreen;
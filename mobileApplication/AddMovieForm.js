/**
 * Created by Adina on 12/2/2016.
 */
import ModalTemplate from './ModalTemplate'

import React, {Component} from 'react';
import {
    TouchableHighlight,
    Image,
    AppRegistry,
    StyleSheet,
    Text,
    View,
    TextInput,
    AsyncStorage
    
} from 'react-native';
import Button from 'react-native-button'
import store from 'react-native-simple-store';

export default class AddMovieForm extends React.Component{
    constructor(props){
        super(props);
        this.state={
            title: '',
            year:''
        }
    }

    onAddClick(){
       /* try {
        await AsyncStorage.setItem(this.state.title, this.state.year);
        this.setState={title: null, year:null}
        //this.props.onAddClick();
        } catch (error) {
        // Error saving data*/
        store
            .save(this.state.title, {title: this.state.title, year: this.state.year}).then(()=>this.props.loadData()).then(()=>this.props.navigator.push({index:0}))

        
       // }
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
                
                <Button
                    style={{fontSize: 20, color: 'white', backgroundColor:'red'}}
                    styleDisabled={{color: 'red'}}
                    //onPress={()=> this.props.navigator.push({index:3})}>
                    onPress ={this.onAddClick.bind(this)}>
                    Add Movie
                </Button>
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

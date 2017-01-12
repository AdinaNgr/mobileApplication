/**
 * Created by Adina on 12/2/2016.
 */
import ModalTemplate from './ModalTemplate'
import { moviesRef } from './ref';
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
            rating:''
        }
    }

    componentDidMount() {
       
    }

    onAddClick(){
        /*Offline persistence */
         var ratingInt = parseInt(this.state.rating)
        // store
        //     .save(this.state.title, ratingInt).then(()=>this.props.loadData()).then(()=>this.props.navigator.push({index:0}));
        const newMovie = {
                        title: this.state.title,
                        rating: ratingInt };
          
      /*Add to Firebase*/
       console.log('New movie is adding to firebase:', newMovie.title, newMovie.rating);
       moviesRef.push(newMovie).then(() => this.props.loadData()).then(()=>this.props.navigator.push({index:0}));


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
                <Text style={styles.description}> Your rating: </Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(rating) => this.setState({rating})}
                    value={this.state.yeratingar}
                   
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

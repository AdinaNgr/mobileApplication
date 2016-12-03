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
    TextInput,
    Button
} from 'react-native';
import store from 'react-native-simple-store';

export default class EditMovieForm extends React.Component{

    constructor(props){
        super(props);
        const movie = this.getMovie(this.props.title)
        this.state={
            //title: movie.title,
           // year: movie.year
           movie: movie
        }
    }
    onEditClick(){
        store.get(this.props.title).then((movie) => {
				store.update(this.props.title, {
				'title': this.state.title, 'year':this.state.year});
		});

        this.props.refreshMovies(); // ii trimit <EditMovieForm refreshMovie = {this.refreshMovies.bind(this)}

    }
    getMovie(title){
        store.get(title).then(movie=> {return movie})
    }
    render(){
        return(
            <View style={styles.container}>
                <Text style={styles.description}> Title: </Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(title) => this.setState({title})}
                    value={this.state.movie}
                />
                <Text style={styles.description}> Release Year: </Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(title) => this.setState({title})}
                    value={this.state.movie}
                />
                <Button
                    style={{fontSize: 20, color: 'white', backgroundColor:'green'}}
                    styleDisabled={{color: 'red'}}
                    //onPress={()=> this.props.navigator.push({index:3})}>
                    onPress ={this.onEditClick.bind(this)}>
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
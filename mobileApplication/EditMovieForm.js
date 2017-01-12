import React, {Component} from 'react';
import {
    TouchableHighlight,
    Image,
    AppRegistry,
    StyleSheet,
    Text,
    View,
    TextInput,
    AsyncStorage,
    Picker,
    JSONObject
    
} from 'react-native';
import Button from 'react-native-button'
import store from 'react-native-simple-store';
import { moviesRef } from './ref';

const Item = Picker.Item;
export default class EditMovieForm extends React.Component{

    constructor(props){
        super(props);
        this.state={
            title: this.props.title,
            rating: this.props.rating,
            movieKey: this.props.movieKey
            
        }
    }
    componentDidMount(){
        console.log("Key:", this.props.movieKey)
        console.log("Title:", this.state.title)
        console.log("Rating:", this.state.rating)
        // if (this.state.title != null) {
        //     AsyncStorage.getItem(this.state.title).then((rating)=> {console.log("before parsing: ", rating); 
          
        //     var ratingInt = parseInt(rating); this.setState({rating: "" + ratingInt}); console.log("Did mount ",this.state.rating); });
        // }

    }
    onEditClick(){
            console.log("EDIT",this.state.title);
            console.log("EDIT",this.state.rating);
            // AsyncStorage.setItem(this.state.title, ""+this.state.rating).then(console.log("UPDATED to rating:", this.state.rating, "movie: ", this.state.title));
            const movieKey = this.props.movieKey;
            moviesRef.child(movieKey).update({ title: this.state.title, rating: this.state.rating });

  }

   change(rating){
       console.log("Value changed before int to:",rating);
       var ratingInt = parseInt(rating);
    console.log("Value changed after int to:",ratingInt);

       this.setState({
           rating: ratingInt
       })
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
                <Text style={styles.description}> Rating: </Text>
                
                 <Picker
                    style={styles.picker}
                    selectedValue={""+this.state.rating}
                    onValueChange={this.change.bind(this)}
                    mode="dropdown">
                        <Item label="5" value="5" />
                        <Item label="6" value="6"/>
                        <Item label="7" value="7" />
                        <Item label="8" value="8" />
                        <Item label="9" value="9" />
                        <Item label="10" value="10" />    
                </Picker>
                <Button
                    style={{fontSize: 20, color: 'white', backgroundColor:'red'}}
                    styleDisabled={{color: 'red'}}
                    //onPress={()=> this.props.navigator.push({index:3})}>
                    onPress ={()=>{this.onEditClick()}}>
                    Edit
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
    },
     picker: {
    width: 100,
  },
});
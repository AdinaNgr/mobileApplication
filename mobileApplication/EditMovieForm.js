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
    Picker
    
} from 'react-native';
import Button from 'react-native-button'

const Item = Picker.Item;
export default class EditMovieForm extends React.Component{

    constructor(props){
        super(props);
        //   const value =  AsyncStorage.getItem('Dark KnighTttttt');
        //     if (value !== null){
        //         // We have data!!
        //         this.setState({year: 'value'})
        //     }
        this.state={
            title: this.props.title,
            rating: AsyncStorage.getItem('Dark KnighTttttt')
            
        }
    }
    componentWillMount(){}
           
    onEditClick(){
        AsyncStorage.getItem(this.state.title, () => {
            AsyncStorage.mergeItem(this.state.title, this.state.rating)});
            this.props.navigator.push({index:0})
  }
          
    // }
    //  componentWillMount(){
    //      var movies  = [];
    //      AsyncStorage.getAllKeys((err, keys) => {
    //         AsyncStorage.multiGet(keys, (err, stores) => {
    //         stores.map((result, i, store) => {
    //             // get at each store's key/value so you can work with it
    //             let key = store[i][0];
    //             let value = store[i][1];
    //             movies.push(value);
    //             });
    //         });

    //     });
    //       for(var i = 0; i < movies.length; i++) {
    //           if(movies[i].title === this.props.title){
    //               this.setState({year: movies[i].year})
    //           }
    //       }
    //   var value =  AsyncStorage.getItem('Dark KnighT');
    //    if (value !== null){
    //     this.setState({year: value});
    //  }} 

    //     AsyncStorage.getItem(this.props.title, (err, movie) => {
    //          this.setState({ year: movie.year })
    // });
   // }
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
                    selectedValue={this.state.rating}
                    onValueChange={(rating) => this.setState({rating})}
                    mode="dropdown">
                        <Item label="5" value="5" />
                        <Item label="6" value="6" />
                        <Item label="7" value="7" />
                        <Item label="8" value="8" />
                        <Item label="9" value="9" />
                        <Item label="10" value="10" />    
                </Picker>
                <Button
                    style={{fontSize: 20, color: 'white', backgroundColor:'red'}}
                    styleDisabled={{color: 'red'}}
                    //onPress={()=> this.props.navigator.push({index:3})}>
                    onPress ={this.onEditClick.bind(this)}>
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
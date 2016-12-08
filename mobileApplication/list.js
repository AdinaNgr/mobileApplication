/**
 * Created by Adina on 11/3/2016.
 */

import React from 'react';
import {
    View,
    ListView,
    StyleSheet,
    Navigator,
    TouchableOpacity,
    Text,
    Modal,
    AsyncStorage
} from 'react-native';

import * as Progress from 'react-native-progress';
import Button from 'react-native-button'
import store from 'react-native-simple-store';
class ListScreen extends React.Component{
    
    constructor(props){
        
        super(props);

        const ds = new ListView.DataSource({
            rowHasChanged: (r1, r2) => r1 != r2
        });
     AsyncStorage.setItem('Dark Knight', '9');
        this.state = {
            dataSource: ds.cloneWithRows(['row1', 'row2']),
            myMovies: [],
            loaded: false,
            isModalOpen: true
        };
    
    
    
  };
_appendMessage(message) {
    this.setState({messages: this.state.messages.concat(message)});
  };

  loadData(){
       store.keys().then((result) => { console.log('Initial :', result),
		 this.setState({
                    dataSource: this.state.dataSource.cloneWithRows(result),
                    loaded: true// true
		                });
        })

       }

    componentDidMount(){
       this.loadData();
    }

    handlePressList(movie){
        this.props.navigator.push({index:1,
            passProps: {
                year: movie.movieYear
            }
        })}

    fetchData(){
        fetch('https://facebook.github.io/react-native/movies.json')
            .then( (response) => response.json())
            .then( (responseData) => {
                this.setState({
                    dataSource: this.state.dataSource.cloneWithRows(responseData.movies),
                    loaded: true,
                });
            })
            .done();
    }

 deleteMovie(title){
     AsyncStorage.removeItem(title);
     this.loadData();
        /*console.log('current title:', title);
        var items =[];
        AsyncStorage.getAllKeys((err, keys) => {
            AsyncStorage.multiGet(keys, (err, stores) => {
                return items = 
            stores.map((result, i, store) => {
                // get at each store's key/value so you can work with it
                let key = store[i][0];
                let value = store[i][1];
                //console.log('Title:', key);
                console.log('Value :', value);
                if(key!==title){
                    console.log('pushed', key, i);
                    return key;
                    //items.push(key);
                    
                }
                })
            }).then((items) => console.log('Length after1: ', items.length, items),
            AsyncStorage.removeItem(title),
            this.loadData(),
             );
        });
        console.log('Length after2: ', items.length);*/
        
       
        

    } 
    removeKeys(){
        AsyncStorage.getAllKeys((err, keys) => {
            AsyncStorage.multiRemove(keys, (err) => {this.loadData();});
        })
        
    }
    render(){
        if(!this.state.loaded)
        {
            return(<View style={styles.progress}>
                    <Text> Please wait...</Text>
                    <Progress.Bar progress={0.3} width={200} indeterminate={true}/>
                    </View>);
        }
        
        return(
            <View style={{flex: 3}}>
            <ListView style={styles.container1}
                        enableEmptySections={true}
                        dataSource={this.state.dataSource}
                        renderRow=
                            {(movie)=>
                            <TouchableOpacity onPress={()=> this.props.navigator.push({index:5, passProps:{title: movie}
                                    
                                })
                            }>
                                <View>
                                    <Text style={styles.symbol}> {movie}</Text>
                                     <Button
                                        style={{fontSize: 20, color: 'green',width:120,height:40, alignItems: 'flex-end'}}
                                        styleDisabled={{color: 'red'}}
                                        onPress={() =>this.deleteMovie(movie)}>
                                        Delete
                                    </Button>
                                      <Button
                                        style={{fontSize: 20, color: 'blue',width:120,height:40, alignItems: 'flex-end'}}
                                        styleDisabled={{color: 'red'}}
                                        onPress={()=> this.props.navigator.push({index:4,
                                    passProps:
                                    {
                                        title: movie
                                    }})}>
                                        Update
                                    </Button>
                                   
                                    
                                </View>
                        </TouchableOpacity>
                         }
                renderSeparator={(sectionID, rowID, adjacentRowHighlighted)=>
                     <View key={rowID} style={{height: 1, backgroundColor:'lightgray'}}/>}

            />
                 <Button
                            style={{fontSize: 20, color: 'green',width:120,height:40, alignItems: 'flex-end'}}
                            styleDisabled={{color: 'green'}}
                            onPress={this.removeKeys.bind(this)}>
                            Delete All
                </Button>
                <Button
                    style={{fontSize: 20, color: 'green'}}
                    styleDisabled={{color: 'red'}}
                    onPress={()=> this.props.navigator.push({index:2})}>
                    Send email!
                </Button>
                <Button
                    style={{fontSize: 20, color: 'white', backgroundColor:'red'}}
                    styleDisabled={{color: 'red'}}
                    onPress={()=> this.props.navigator.push({index:3, passProps:{loadData: this.loadData.bind(this)}})
                }>
                    Add Movie
                </Button>

                 
            </View>
    );
    }
}
const styles = StyleSheet.create({
    separator: {
        flex: 1,
        height: StyleSheet.hairlineWidth,
        backgroundColor: '#8E8E8E',
    },
    progress: {
        marginTop: 80,
    },
    container1: {
        flex: 1,
        marginTop: 20,
    },
    container: {
        flex: 1,
        flexDirection: 'row',
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
    },
    rightContainer: {
        flex: 1,
    },
    title: {
        fontSize: 20,
        marginBottom: 8,
        textAlign: 'center',
    },
    year: {
        textAlign: 'center',
    },
    thumbnail: {
        width: 53,
        height: 81,
    },
    listView: {
        paddingTop: 20,
        backgroundColor: '#F5FCFF',
    },
});

export default ListScreen;
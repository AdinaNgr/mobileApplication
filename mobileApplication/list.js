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
    Text
} from 'react-native';

import * as Progress from 'react-native-progress';
import Button from 'react-native-button'

class ListScreen extends React.Component{
    constructor(props){
        super(props);

        const ds = new ListView.DataSource({
            rowHasChanged: (r1, r2) => r1 != r2
        });

        this.state = {
            dataSource: ds.cloneWithRows(['row1', 'row2']),
            myMovies: [],
            loaded: false
        };
    }

    componentDidMount(){
        this.fetchData();
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
    render(){
        if(!this.state.loaded)
        {
            return(<View style={styles.progress}>
                    <Text> Please wait...</Text>
                    <Progress.Bar progress={0.3} width={200} indeterminate={true}/>
                    </View>);
        }

        return(
            <View style={{flex: 1}}>
            <ListView style={styles.container1}
                        enableEmptySections={true}
                        dataSource={this.state.dataSource}
                        renderRow=
                            {(movie)=>
                            <TouchableOpacity onPress={()=> this.props.navigator.push({index:1,
                                    passProps:
                                    {
                                        title: movie.title,
                                        year: movie.releaseYear
                                    }})
                            }>
                                <View>
                                    <Text style={styles.symbol}> {movie.title}</Text>
                                </View>
                        </TouchableOpacity>
                         }
                renderSeparator={(sectionID, rowID, adjacentRowHighlighted)=>
                     <View key={rowID} style={{height: 1, backgroundColor:'lightgray'}}/>}

            />

                <Button
                    style={{fontSize: 20, color: 'green'}}
                    styleDisabled={{color: 'red'}}
                    onPress={()=> this.props.navigator.push({index:2})}>
                    Send email!
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
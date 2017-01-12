/**
 * Created by Adina on 11/3/2016.
 */

import React, {Component} from 'react';
import {
    Navigator,
    StatusBar,
    TouchableHighlight,
    AppRegistry,
    StyleSheet,
    Text,
    View,
    AsyncStorage
} from 'react-native';

import ListScreen from './list.js';
import DetailScreen from './details.js';
import GmailForm from './GmailForm.js';
import AddMovieForm from './AddMovieForm';
import EditMovieForm from './EditMovieForm';
import SimpleChart from './chart';
import SignUp from './signup';
import Login from './login';


const routes =[
    {
        title: 'Movie List:',
        index: 0
    },
    {
        title: 'Movie Details: ',
        index: 1
    },
    {
        title: 'Send Email',
        index: 2
    },
    {
        title: 'Add Movie',
        index: 3
    },
    {
        title: 'Edit Movie',
        index: 4
    },
    {
        title: 'Chart',
        index: 5
    },
    {
        title: 'SignUp',
        index:6
    },
    {
        title: 'Login',
        index: 7
    }
];
    class App extends Component {
        render() {
            return (
                <View style={styles.container}>
        <StatusBar
            backgroundColor="black"
            barStyle="light-content"
                />
                <Navigator
            initialRoute={routes[6]}
            initialRouteStack={routes}
            renderScene={
            (route, navigator) => {
                switch (route.index)
                {
                    case 0: return (<ListScreen navigator={navigator} route={routes[route.index]} {...route.passProps}></ListScreen>);
                    case 1: return (<DetailScreen navigator={navigator} route={routes[route.index]} {...route.passProps}></DetailScreen>);
                    case 2: return (<GmailForm navigator={navigator} route={routes[route.index]} {...route.passProps}></GmailForm>);
                    case 3: return (<AddMovieForm  navigator={navigator} route={routes[route.index]} {...route.passProps}></AddMovieForm>);
                    case 4: return (<EditMovieForm navigator={navigator} route={routes[route.index]} {...route.passProps}></EditMovieForm>);
                    case 5: return (<SimpleChart navigator={navigator} route={routes[route.index]} {...route.passProps}></SimpleChart>)
                    case 6: return (<SignUp navigator={navigator} route={routes[route.index]} {...route.passProps}></SignUp>);
                    case 7: return (<Login  navigator={navigator} route={routes[route.ndex]} {...route.passProps}></Login>);
                }
            }
        }
            configureScene={
            (route, routeStack) =>
            Navigator.SceneConfigs.FloatFromBottom
        }
            navigationBar={
                <Navigator.NavigationBar
            routeMapper={{
                LeftButton: (route, navigator, index, navState) => {
                    if (route.index == 0){
                        return null;
                    }
                    return (
                        <TouchableHighlight onPress={()=>navigator.pop()}>
                    <Text style={styles.navigationBarText}>Back</Text>
                    </TouchableHighlight>
                    )
                },
                    RightButton: (route, navigator, index, navState) => { return null; },
                    Title: (route, navigator, index, navState) =>
                { return (<Text style={[styles.navigationBarText, styles.titleText]}>{routes[route.index].title}</Text>); },
            }}
            style={styles.navigationBar}
        />
        }
        />
        </View>
        );
        }
    }

    const styles = StyleSheet.create({
        container: {
            flex: 1
        },
        navigationBar:{
            backgroundColor: 'black',
        },
        navigationBarText:{
            color: 'white',
            padding: 10,
            fontSize: 15,
            backgroundColor: 'black'
        },
        titleText:{
            fontSize: 20,
            paddingTop:5
        }

    });

AppRegistry.registerComponent('mobileApplication', () => App);

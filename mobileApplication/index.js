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
    View
} from 'react-native';

import ListScreen from './list.js';
import DetailScreen from './details.js';

var MOCKED_MOVIES_DATA = [
    {title: 'Titanic', year: '2015', posters: {thumbnail: 'http://i.imgur.com/UePbdph.jpg'}},
];
const routes = [
    {
        title: 'Movie List',
        index: 0
    },
    {
        title: 'Movie Details',
        index: 1
    }
];
    class App extends Component {
        render() {
            return (
                <View style={styles.container}>
        <StatusBar
            backgroundColor="darkred"
            barStyle="light-content"
                />
                <Navigator
            initialRoute={routes[0]}
            initialRouteStack={routes}
            renderScene={
            (route, navigator) => {
                switch (route.index) {
                    case 0: return (<ListScreen navigator={navigator} route={routes[route.index]} {...route.passProps}></ListScreen>);
                    case 1: return (<DetailScreen navigator={navigator} route={routes[route.index]} {...route.passProps}></DetailScreen>);
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
            fontSize: 15
        },
        titleText:{
            fontSize: 20,
            paddingTop:5
        }

    });

    AppRegistry.registerComponent('mobileApplication', () => App);
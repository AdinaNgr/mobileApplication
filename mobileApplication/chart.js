import React, { Component } from 'react'; 
import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import Chart from 'react-native-chart';
import { Dimensions } from 'react-native';
import store from 'react-native-simple-store';
var {height, width} = Dimensions.get('window');

const styles = StyleSheet.create({
    container: {
        height: height/8,
        width: width,
        alignItems: 'center',
        backgroundColor: 'white',
        padding:60,
        flex:1
    },
    chart: {
        width:200, height:100,
        padding:30,
        flex: 4
    },
     container2: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
});
const data = [];

export default class SimpleChart extends React.Component {
    constructor(props){
        super(props);
       this.state={
           title: this.props.title
       }
    }
    getRandomInt(min, max) {
      return Math.floor(Math.random() * (max - min + 1)) + min;
    }
    
    componentWillMount(){
        
        //store.get(this.state.title).then((movie)=> setState({rating: this.rating})) => THE BIND VALUE AT INDEX 1 IS NULL
        data.push([this.state.title,this.getRandomInt(1,10)]); 
        
    }
    render() {
        return (
             
            <View style={styles.container2}>
       
            <View style={styles.container}>
                <Chart
                    style={styles.chart}
                    data={data}
                    type="bar"
                    showDataPoint={true}
                    showGrid={false}
                 />
            </View>
           
            
	   
           </View>
           
        );
    }
}

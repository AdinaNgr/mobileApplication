/**
 * Created by Adina on 11/4/2016.
 */
'use strict';

var React = require('react-native');

var {
    AppRegistry,
    StyleSheet,
    DeviceEventEmitter,
    ListView,
    Text,
    TouchableHighlight,
    View,
} = React;

var testData = [{"firstName":"Black","lastName":"Garrett"},
    {"firstName":"Morales","lastName":"Duncan"},
    {"firstName":"Ramos","lastName":"King"},
    {"firstName":"Dunn","lastName":"Collins"},
    {"firstName":"Fernandez","lastName":"Montgomery"},
    {"firstName":"Burns","lastName":"Fox"},
    {"firstName":"Richardson","lastName":"Kim"},
    {"firstName":"Hanson","lastName":"Evans"},
    {"firstName":"Anderson","lastName":"Hunt"},
    {"firstName":"Carter","lastName":"Grant"},
    {"firstName":"Ray","lastName":"Ruiz"},
    {"firstName":"Hart","lastName":"Schmidt"},
    {"firstName":"White","lastName":"Andrews"},
    {"firstName":"Hall","lastName":"Holmes"},
    {"firstName":"Hawkins","lastName":"Gomez"},
    {"firstName":"Bowman","lastName":"Sullivan"},
    {"firstName":"Brooks","lastName":"Evans"},
    {"firstName":"Reyes","lastName":"Perez"},
    {"firstName":"Dixon","lastName":"Barnes"},
    {"firstName":"Ward","lastName":"Lee"},
    {"firstName":"Berry","lastName":"Payne"},
    {"firstName":"Murray","lastName":"Rose"},
    {"firstName":"Stephens","lastName":"Fowler"},
    {"firstName":"Rodriguez","lastName":"Lewis"},
    {"firstName":"Cook","lastName":"Dean"}];


// Specify your own logic for sorting the datasource objects
function compare(a,b) {
    if (a.lastName < b.lastName)
        return -1;
    if (a.lastName > b.lastName)
        return 1;
    return 0;
}

var SampleRow = React.createClass({
    render() {
        return (
            <View style={styles.wrapper}>
    <View>
        <Text style={styles.text}>{this.props.lastName}, {this.props.firstName}</Text>
        </View>
        </View>
    );
    }
});

var ListViewRowsAndSections = React.createClass({
    getInitialState: function() {
        var ds = new ListView.DataSource({
            sectionHeaderHasChanged: (r1, r2) => r1 !== r2,
            rowHasChanged: (r1, r2) => r1 !== r2
        });

        var {data, sectionIds} = this.renderListViewData(testData.sort(compare));

        return {
            dataSource: ds.cloneWithRowsAndSections(data, sectionIds)
        };
    },

    componentDidMount() {
        var listViewScrollView = this.refs.listView.getScrollResponder();
        // listViewScrollView.scrollTo(1); // Hack to get ListView to render fully
    },

    renderListViewData(users) {
        var data = {};
        var sectionIds = [];

        users.map((user) => {
            var section = user.lastName.charAt(0);
            if (sectionIds.indexOf(section) === -1) {
                sectionIds.push(section);
                data[section] = [];
            }
            data[section].push(user);
        });

        return {data, sectionIds};
    },

    renderSectionHeader(data, sectionId) {
        var text;
        return (
            <View style={styles.sectionHeader}>
    <Text style={styles.sectionHeaderText}>{sectionId}</Text>
        </View>
    );
    },

    renderRow(rowData) {
        return <SampleRow {...rowData} style={styles.row} />
    },

    render() {
        return (
            <ListView
        ref="listView"
        automaticallyAdjustContentInsets={false}
        dataSource={this.state.dataSource}
        renderRow={this.renderRow}
        renderSectionHeader={this.renderSectionHeader}
    />
    );
    },
});


var styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        backgroundColor: '#F5FCFF',
    },
    wrapper: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        paddingRight: 10,
        borderBottomWidth: 1,
        borderBottomColor: '#e9e9e9',
    },
    text: {
        fontSize: 24,
        fontWeight: "100",
        color: 'black',
    },
    sectionHeader: {
        backgroundColor: '#48D1CC'
    },
    sectionHeaderText: {
        fontFamily: 'AvenirNext-Medium',
        fontSize: 16,
        color: 'white',
        paddingLeft: 10
    },
});

AppRegistry.registerComponent('mobileApplication', () => ListViewRowsAndSections);

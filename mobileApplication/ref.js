import firebase from 'firebase';

const firebaseConfig = ({
    apiKey: "AIzaSyABz24duMXiMXXnXdmmFby2MMurMP2UYB8",
    authDomain: "movieapp-965d2.firebaseapp.com",
    databaseURL: "https://movieapp-965d2.firebaseio.com",
    storageBucket: "movieapp-965d2.appspot.com",
    messagingSenderId: "818563717031"
});

const firebaseApp = firebase.initializeApp(firebaseConfig);
const rootRef = firebase.database().ref();
export const moviesRef = rootRef.child('movies');
import Firebase from 'firebase';
import 'firebase/auth';
import 'firebase/firestore';

const config = {
 <user's firebase configuration settings
};
// authDomain, databaseURL, storageBucket, messagingSenderID, appID, measurementID are probably wrong and were directly copied from Isabella's spike exercise
let app = Firebase.initializeApp(config);
export const db = app.database();

import 'react-native-gesture-handler';
import { StyleSheet } from 'react-native';
import ContactList from './src/components/ContactList';
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import AddNewContact from './src/components/AddNewContact';
import UpdateContact from './src/components/UpdateContact';
import FavouriteContactList from './src/components/FavouriteContactList';
import { createDrawerNavigator } from '@react-navigation/drawer';
const Stack = createNativeStackNavigator();
const Drawer = createDrawerNavigator();

const ContactStackNavigator = () => {
  return (
    <Stack.Navigator>
      <Stack.Screen name="ContactList" component={ContactList} />
      <Stack.Screen name="AddNewContact" component={AddNewContact} />
      <Stack.Screen name="UpdateContact" component={UpdateContact} />
    </Stack.Navigator>
  );
};

const FavouriteListStack = () => {
  return (
    <Stack.Navigator>
      <Stack.Screen name="FavouriteContactList" component={FavouriteContactList} />     
       <Stack.Screen name="UpdateContact" component={UpdateContact} />
    </Stack.Navigator>
  );
};

export default function App() {
  
  return (
    <NavigationContainer>
     <Drawer.Navigator screenOptions={{ headerShown: false }}>
        <Drawer.Screen name="Contacts" component={ContactStackNavigator} />
        <Drawer.Screen name="Favourite Contacts" component={FavouriteListStack} />
      </Drawer.Navigator>
    </NavigationContainer>
  );
}


const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});

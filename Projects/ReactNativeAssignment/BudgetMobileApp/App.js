import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { StyleSheet } from 'react-native';
import Homepage from './src/components/homepage/homepage';
import BudgetList from "./src/components/budgetlist/budgetlist";
import {Provider} from'react-redux' ;
import configureStore from './src/redux/store'
const Stack = createNativeStackNavigator();


export default function App() {
  store = configureStore();
  return (
    <Provider store={store}>
      <NavigationContainer>
      <Stack.Navigator
        screenOptions={{
          headerShown: false, // Hide the header for all screens
        }}
      >
      <Stack.Screen name="Home" component={Homepage} />
      <Stack.Screen name="BudgetList" component={BudgetList} />
      </Stack.Navigator>
    </NavigationContainer>
    </Provider>
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
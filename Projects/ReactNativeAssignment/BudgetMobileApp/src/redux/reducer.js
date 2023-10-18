import AsyncStorage from '@react-native-async-storage/async-storage';

const initialState = {
  budgetEntry: []
};

export const budgetReducer = (state = initialState, action) => {
  switch (action.type) {
    case 'SET_FORM_DATA':
      const itemName = action.payload.itemName;
      const plannedAmount = action.payload.plannedAmount;
      const actualAmount = action.payload.actualAmount;
      const newState = {
        budgetEntry: [...state.budgetEntry, { itemName, plannedAmount, actualAmount }]
      };
      storeData(newState);
      return newState;
    default:
      return state;
  }
};

const storeData = async (newState) => {
  try {
    const value = await AsyncStorage.getItem('state');
    let state = {};
    if (value) {
      state = JSON.parse(value); // Parse the stored string back into an object
    }

    // Merge the existing state with the new state
    state = { ...state, budgetEntry: [...state.budgetEntry, ...newState.budgetEntry] };
    await AsyncStorage.setItem('state', JSON.stringify(state)); 
    console.log("State saved successfully");
  } catch (error) {
    console.log("Error saving state:", error);
  }
};



  




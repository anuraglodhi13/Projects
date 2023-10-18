import React, { useState } from 'react';
import { useDispatch } from 'react-redux'
import { View, Text, TouchableOpacity, TextInput, StyleSheet  } from 'react-native'
import { setBudgetEntry } from '../../redux/action';
export default function Homepage({ navigation }) {
  const [formData, setFormData] = useState({
    itemName: '',
    plannedAmount: '',
    actualAmount: '',
  });
  const dispatch = useDispatch();
  const [error, setError] = useState('');
  const handleChange = (fieldName, value) => {
    setFormData((prevData) => ({
      ...prevData,
      [fieldName]: value,
    }));
  };

  const handleSubmit = () => {
    if (!formData.itemName || !formData.plannedAmount || !formData.actualAmount) {
      setError('Please fill in all the fields.');
      return;
    }
    dispatch(setBudgetEntry(formData.itemName,formData.plannedAmount,formData.actualAmount));
    setError('');

    // Reset input fields
    
    setFormData({
      itemName: '',
      plannedAmount: '',
      actualAmount: '',
    });
  };

  const handleShowList = () => {
    // Perform action to show the list
    navigation.navigate("BudgetList")
  };

  return (
      
    <View style={styles.formContainer}>
    <Text style={styles.heading}>Budget Entry</Text>
      <View style={styles.row}>
        <Text style={styles.label}>Item Name:</Text>
        <TextInput
          style={styles.input}
          value={formData.itemName}
          onChangeText={(value) => handleChange('itemName', value)}
          placeholder="Enter Item Name"
        />
      </View>
      <View style={styles.row}>
        <Text style={styles.label}>Planned Amount:</Text>
        <TextInput
          style={styles.input}
          value={formData.plannedAmount}
          onChangeText={(value) => handleChange('plannedAmount', value)}
          placeholder="Enter Planned Amount"
        />
      </View>
      <View style={styles.row}>
        <Text style={styles.label}>Actual Amount:</Text>
        <TextInput
          style={styles.input}
          value={formData.actualAmount}
          onChangeText={(value) => handleChange('actualAmount', value)}
          placeholder="Enter Actual Amount"
        />
      </View>
      {error ? <Text style={styles.errorText}>{error}</Text> : null}
      <View style={styles.buttonContainer}>
          <TouchableOpacity style={styles.button} onPress={handleSubmit}>
            <Text style={styles.buttonText}>Submit</Text>
          </TouchableOpacity>
          <TouchableOpacity style={[styles.button, { backgroundColor: 'green' }]} onPress={handleShowList}>
            <Text style={styles.buttonText}>Show List</Text>
          </TouchableOpacity>
        </View>
        </View>  
        );
};

const styles = StyleSheet.create({
  container: {
    flexGrow: 1,
    padding: 16,
    marginBottom: 0,
  },
  heading: {
    fontSize: 24,
    fontWeight: 'bold',
    textAlign: 'center',
    marginBottom:40,
  },
  formContainer: {
    flex: 1,
    justifyContent: 'center',
  },
  row: {
    marginBottom: 12,
  },
  label: {
    fontSize: 16,
    marginBottom: 8,
    marginLeft:30,
  },
  input: {
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 4,
    paddingVertical: 10,
    fontSize: 16,
    marginLeft:30,
    marginRight:40,
  },
  buttonContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginTop: 16,
    marginLeft:35,
    marginRight:45,
  },
  button: {
    backgroundColor: '#2196F3',
    borderRadius: 4,
    paddingVertical: 12,
    paddingHorizontal: 16,
    alignItems: 'center',
    flex: 1,
    marginRight: 8,
  },
  buttonText: {
    color: 'white',
    fontSize: 16,
    fontWeight: 'bold',
  },
  errorText: {
    color: 'red',
    fontSize: 16,
    marginLeft: 30,
  },
});

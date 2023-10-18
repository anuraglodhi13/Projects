import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet,FlatList  } from 'react-native'
import AsyncStorage from '@react-native-async-storage/async-storage';

export default function BudgetList() {
  const [nameList, setNameList] = useState([]);
  useEffect(() => {
    retrieveData();
  }, []);

  const retrieveData = async () => {
      try {
        const value = await AsyncStorage.getItem('state'); 
        if (value) {
          const state = JSON.parse(value); 
          if (state.budgetEntry && state.budgetEntry.length > 0) {
            const itemNameList = state.budgetEntry.map((entry) => entry.itemName);
            setNameList(itemNameList);
            }
           else {
            console.log("No budgetEntry found or it is empty.");
         }
        } else {
          console.log("No stored state found.");
        }
      } catch (error) {
        console.log("Error retrieving state:", error);
      }
    };

    return (
      <View style={styles.formContainer}>
        <Text style={styles.heading}>Budget Entry Listing</Text>
        {nameList.length === 0 ? (
          <Text style={styles.errorText}>No budget entry is present.</Text>
        ) : (
          <View style={styles.listContainer}>
            <FlatList
              data={nameList}
              renderItem={({ item }) => (
                <View style={styles.nameContainer}>
                  <Text style={styles.nameText}>{item}</Text>
                </View>
              )}
              keyExtractor={(item, index) => index.toString()}
            />
          </View>
        )}
      </View>
    );
};

const styles = StyleSheet.create({
    name: {
        fontSize: 16,
        marginBottom: 8,
      },
  heading: {
    fontSize: 24,
    fontWeight: 'bold',
    textAlign: 'center',
    marginBottom: 40,
  },
  formContainer: {
    marginBottom:150,
    flex: 1,
    justifyContent: 'center',
  },
  container: {
    padding: 16,
  },
  nameContainer: {
    marginBottom: 8,
  },
  nameText: {
    fontSize: 16,
    marginLeft:170
  },
  listContainer: {
    height: 450, 
  },
  errorText: {
    fontSize: 16,
    textAlign: 'center',
    marginBottom: 8,
    color: 'red',
  }
});


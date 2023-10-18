import React, {useState,useCallback,useEffect } from 'react';
import { View, Text, TouchableOpacity, StyleSheet, ScrollView, Image, Animated } from 'react-native';
import Icon from 'react-native-vector-icons/Entypo';
import { Swipeable } from 'react-native-gesture-handler';
import { getDBConnection, getPersons,deletePerson } from '../database/db-service';
import { useFocusEffect } from '@react-navigation/native';

export default function ContactList({ navigation }) {
  const [contacts, setContacts] = useState([]);

  const handleContactClick = (id) => {
    navigation.navigate('UpdateContact', { id });
  }
  const handleButtonPress = () => {
    navigation.navigate('AddNewContact');
  };
  const convertBase64ToImageSource = (base64Data) => {
    const imageSource = {
      uri: `data:image/jpeg;base64,${base64Data}`,
    };
    return imageSource;
  };
  useEffect(() => {
    navigation.setOptions({
      headerLeft: () => (
        <TouchableOpacity onPress={() => navigation.openDrawer()}>
          <Icon name="menu" size={24} color="black" style={styles.icon} />
        </TouchableOpacity>
      ),
      headerTitleAlign: 'center',
    });
  }, [navigation]);

  useFocusEffect(
    useCallback(() => {
      const fetchContacts = async () => {
        try {
          const db = await getDBConnection();
          const fetchedContacts = await getPersons(db);
          for (let i = 0; i < fetchedContacts.length; i++) {
            fetchedContacts[i].photo = convertBase64ToImageSource(fetchedContacts[i].photo);
          }
          fetchedContacts.sort((a, b) => a.name.localeCompare(b.name));
          setContacts(fetchedContacts);
        } catch (error) {
          console.error('Error fetching contacts:', error);
        }
      };
  
      fetchContacts();
    }, [])
  );


  const handleDeleteContact = async (id) => {
    const db = await getDBConnection();
    await deletePerson(db, id);
    console.log('Contact deleted with id:', id);
  
    // Refreshing the contact list
    try {
      const db = await getDBConnection();
      const fetchedContacts = await getPersons(db);
      for (let i = 0; i < fetchedContacts.length; i++) {
        fetchedContacts[i].photo = convertBase64ToImageSource(fetchedContacts[i].photo);
      }
      setContacts(fetchedContacts);
    } catch (error) {
      console.error('Error fetching contacts:', error);
    }
  };

  const handleEditContact = (id) => {
    navigation.navigate('UpdateContact', { id });
  };

  const renderRightActions = (progress, dragX, id) => {
    const trans = dragX.interpolate({
      inputRange: [-100, 0],
      outputRange: [1, 0],
    });

    return (
      <View style={styles.rightActions}>
        <TouchableOpacity style={[styles.actionButton, styles.editButton]} onPress={() => handleEditContact(id)}>
          <Animated.Text style={[styles.buttonText, { transform: [{ scale: trans }] }]}>Edit</Animated.Text>
        </TouchableOpacity>
        <TouchableOpacity style={[styles.actionButton, styles.deleteButton]} onPress={() => handleDeleteContact(id)}>
          <Animated.Text style={[styles.buttonText, { transform: [{ scale: trans }] }]}>Delete</Animated.Text>
        </TouchableOpacity>
      </View>
    );
  };

  return (
    <View style={styles.container}>
      <ScrollView style={styles.contactList}>
        {contacts.map((contact, index) => (
          <Swipeable key={index} renderRightActions={(progress, dragX) => renderRightActions(progress, dragX, contact.id)}>
            <TouchableOpacity style={styles.contact} onPress={() => handleContactClick(contact.id)}>
            <Image source={contact.photo} style={styles.contactImage} />
              <View style={styles.contactDetails}>
                <Text style={styles.contactName}>{contact.name}</Text>
              </View>
            </TouchableOpacity>
          </Swipeable>
        ))}
      </ScrollView>
      <TouchableOpacity style={styles.floatingButton} onPress={handleButtonPress}>
        <Icon name="plus" size={24} color="white" style={styles.floatingButtonIcon} />
      </TouchableOpacity>
    </View>
  );
}


const styles = StyleSheet.create({
  container: {
    flexGrow: 1,
    padding: 16,
    marginBottom: 0,
  },
  floatingButton: {
    position: 'absolute',
    bottom: 16,
    right: 16,
    width: 56,
    height: 56,
    borderRadius: 28,
    backgroundColor: 'blue',
    justifyContent: 'center',
    alignItems: 'center',
    elevation: 4,
  },
  floatingButtonIcon: {
    alignSelf: 'center',
  },
  contactList: {
    flex: 1,
    marginLeft: 70,
  },
  contact: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 40,
  },
  contactImage: {
    width: 48,
    height: 48,
    borderRadius: 24,
    marginRight: 8,
  },
  contactName: {
    fontSize: 16,
    fontWeight: 'bold',
    color: 'black',
  },
  contactDetails: {
    marginLeft: 30,
  },
  rightActions: {
    flexDirection: 'row',
  },
  actionButton: {
    width: 100,
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 40,
    fontSize: 16,
  },
  editButton: {
    backgroundColor: 'orange',
  },
  deleteButton: {
    backgroundColor: 'red',
  },
  buttonText: {
    color: 'white',
  },
});
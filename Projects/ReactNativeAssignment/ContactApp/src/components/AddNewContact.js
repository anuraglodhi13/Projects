import React, { useState,useEffect } from 'react';
import { View, TextInput, Button, Image, StyleSheet,TouchableOpacity,Text,Alert } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';
import * as ImagePicker from 'react-native-image-picker';
import { getDBConnection, createTable, savePerson } from '../database/db-service';
import { Person } from '../models/model';
import RNFS from 'react-native-fs';

export default function AddNewContact({navigation}) {
  const [name, setName] = useState('');
  const [mobile, setMobile] = useState('');
  const [email, setEmail] = useState('');
  const [photo, setPhoto] = useState(null);
  const [isFavorite, setIsFavorite] = useState(false);

  useEffect(() => {
    navigation.setOptions({
      headerRight: () => (
        <TouchableOpacity onPress={() => setIsFavorite(!isFavorite)}>
          <Icon name={isFavorite ? 'star' : 'star-o'} size={20} color="black" style={styles.favoriteIcon} />
        </TouchableOpacity>
      ),
      headerTitleAlign: 'center',
    });
  }, [isFavorite, navigation]);

  const handleSave = async () => {
    let photoBase64 = null;
    if (photo) {
      try {
        const response = await RNFS.readFile(photo, 'base64');
        photoBase64 = response;
      } catch (error) {
        console.error('Error converting photo to base64:', error);
      }
    }

    const person = new Person(photoBase64, name, mobile, email, isFavorite);
    saveContactsToDatabase(person)
    .then(() => {
      Alert.alert('Success', 'Contacts saved to the database successfully', [{ text: 'OK' }]);

      setName('');
      setMobile('');
      setEmail('');
      setPhoto(null);
      setIsFavorite(false);
    })
    .catch(error => {
      console.error('Error saving contacts:', error);
    });

  };
  
  const handlePhotoUpload = () => {
    const options = {
      title: 'Select Photo',
      mediaType: 'photo',
      maxWidth: 300,
      maxHeight: 300,
      quality: 1,
    };

    ImagePicker.launchImageLibrary(options, response => {
      if (response.didCancel) {
        console.log('User cancelled image picker');
      } else if (response.error) {
        console.log('Image picker error:', response.error);
      } else {
        
        setPhoto(response.assets[0].uri);
      }
    });
  };

  const saveContactsToDatabase = async (person) => {
    try {
      const db = await getDBConnection();
      await createTable(db);
      await savePerson(db, person);
      console.log('Contacts saved to the database successfully');
    } catch (error) {
      console.error('Error saving contacts:', error);
    }
  };

  
  return (
    <View style={styles.container}>
      
      {photo ? (
        <Image source={{ uri: photo }} style={styles.photo} />
      ) : (
        <TouchableOpacity style={styles.button} onPress={handlePhotoUpload}>
          <View style={styles.buttonWrapper}>
            <Icon name="camera" size={30} color="black" />
          </View>
        </TouchableOpacity>
      )}      
      <TextInput
        style={styles.input}
        placeholder="Name"
        value={name}
        onChangeText={setName}
      />
      
      <TextInput
        style={styles.input}
        placeholder="Mobile"
        value={mobile}
        onChangeText={setMobile}
        keyboardType="phone-pad"
      />
      
      <TextInput
        style={styles.input}
        placeholder="Email"
        value={email}
        onChangeText={setEmail}
        keyboardType="email-address"
      />
      
  <Button title="Save" onPress={handleSave} style={styles.button} />
    </View>
  );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 20,
        alignItems: 'center',
        justifyContent: 'center',
      },
      input: {
        height: 40,
        width: '100%',
        marginBottom: 10,
        paddingHorizontal: 10,
        borderWidth: 1,
        borderColor: 'gray',
      },
      photo: {
        borderRadius: 120,
        width: 120,
        height: 120,
        alignItems: 'center',
        justifyContent: 'center',
        borderWidth: 2, 
        marginBottom: 10,
      },
      button: {
        marginBottom: 100,
      },
      buttonWrapper: {
        backgroundColor: 'white',
        borderRadius: 120,
        width: 120,
        height: 120,
        alignItems: 'center',
        justifyContent: 'center',
        borderColor: 'black', 
    borderWidth: 2, 
      },

});


import SQLite from 'react-native-sqlite-storage';

const tableName = 'personTable';

SQLite.enablePromise(true);

export const getDBConnection = async () => {
  return SQLite.openDatabase({ name: 'person-data.db', location: 'default' });
};

export const createTable = async (db) => {
  // create table if not exists
  const query = `CREATE TABLE IF NOT EXISTS ${tableName}(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    photo TEXT,
    name TEXT NOT NULL,
    mobile TEXT NOT NULL,
    email TEXT,
    favourite INTEGER DEFAULT 0
  );`;

  await db.executeSql(query);
};


export const getPersons = async (db) => {
  try {
    const Persons = [];
    const results = await db.executeSql(`SELECT * FROM ${tableName}`);
    results.forEach((result) => {
      for (let index = 0; index < result.rows.length; index++) {
        Persons.push(result.rows.item(index));
      }
    });
    return Persons;
  } catch (error) {
    console.error(error);
    throw new Error('Failed to get Persons !!!');
  }
};

export const savePerson = async (db, person) => {
  const insertQuery = `INSERT INTO ${tableName}(photo, name, mobile, email, favourite) VALUES (?, ?, ?, ?, ?)`;
  const insertValues = [person.photo, person.name, person.mobile, person.email, person.favourite ? 1 : 0];

  return db.executeSql(insertQuery, insertValues);
};

export const deletePerson = async (db, id) => {
  const deleteQuery = `DELETE from ${tableName} where rowid = ${id}`;
  await db.executeSql(deleteQuery);
};

export const deleteTable = async (db) => {
  const query = `DROP TABLE ${tableName}`;

  await db.executeSql(query);
};

export const getPersonById = async (db, id) => {
  const query = `SELECT * FROM ${tableName} WHERE id = ?`;
  const values = [id];

  const results = await db.executeSql(query, values);

  if (results[0]?.rows?.length > 0) {
    return results[0].rows.item(0);
  }

  throw new Error(`Person with ID ${id} not found`);
};

export const updatePersonById = async (db, id, updatedPerson) => {
  const updateQuery = `UPDATE ${tableName} SET photo = ?, name = ?, mobile = ?, email = ?, favourite = ? WHERE id = ?`;
  const updateValues = [
    updatedPerson.photo,
    updatedPerson.name,
    updatedPerson.mobile,
    updatedPerson.email,
    updatedPerson.favourite ? 1 : 0,
    id,
  ];

  return db.executeSql(updateQuery, updateValues);
};

export const getFavoritePersons = async (db) => {
  try {
    const favoritePersons = [];
    const results = await db.executeSql(`SELECT * FROM ${tableName} WHERE favourite = 1`);
    results.forEach((result) => {
      for (let index = 0; index < result.rows.length; index++) {
        favoritePersons.push(result.rows.item(index));
      }
    });
    return favoritePersons;
  } catch (error) {
    console.error(error);
    throw new Error('Failed to get favorite persons!');
  }
};
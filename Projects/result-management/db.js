var mysql = require('mysql');

//MySQL details
var mysqlConnection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'password',
    database: 'school',
    multipleStatements: true
});

mysqlConnection.connect((err)=> {
    if(!err)
    console.log('Connection Established Successfully');
    else
    console.log('Connection Failed!'+ JSON.stringify(err,undefined,2));
});

module.exports = mysqlConnection;

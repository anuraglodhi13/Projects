
const express = require('express');
var app = express();

const port = process.env.PORT || 9000;
app.listen(port, () => console.log(`Listening on port ${port}..`));

var teacherRouter = require('./route/teacherResult');
var studentRouter = require('./route/studentResult');

// workaround to prevent cors error 
app.use(function (req, res, next) {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');
    res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');
    res.setHeader('Access-Control-Allow-Credentials', true);
    next();
    });
    
app.use('/teacher', teacherRouter);
app.use('/student', studentRouter);








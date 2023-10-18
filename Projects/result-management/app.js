const express = require('express');
var app = express();
var moment = require('moment');
exports.index = function(req, res) {
    res.render('index', { moment: moment });
}
const port = process.env.PORT || 9000;
app.listen(port, () => console.log(`Listening on port ${port}..`));



//register view engine
app.set('view engine', 'ejs');
//middleware and static files
app.use(express.static('public'))
app.use(express.json());
app.use(express.urlencoded());

//express layouts
var expressLayouts = require('express-ejs-layouts');
app.use(expressLayouts);
app.set('layout', 'layouts/layout');

var teacherRouter = require('./route/teacherResult');
var studentRouter = require('./route/studentResult');
    
app.use('/teacher', teacherRouter);
app.use('/student', studentRouter);

//routes
app.get("/", (req, res) => {
  res.render("index",{ moment: moment });
});

// 404 page
app.use((req, res) => {
  res.status(404).render('404', { title: '404' });
});
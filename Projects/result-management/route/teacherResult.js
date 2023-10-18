var express = require('express');
var router = express.Router();
var db = require('../db');
var bodyParser = require('body-parser');
var moment = require('moment');
router.use(bodyParser.json());

// route to get all details of result
router.get('/viewall', function(req, res) {
    var sql = "SELECT * FROM result";
    db.query(sql, function(err, rows) {
      if (err) {
        res.status(500).send({ error: 'Something failed!' })
      }
      res.render('teacher/viewall', {student: rows,addedSuccesfully:false,editedSuccesfully:false,deletedSuccesfully:false});
    })
});

// route to get detail of particular result
router.get('/result/:rollno', function(req, res) {

    var sql = "SELECT * FROM result WHERE rollno ="+[req.params.rollno];

    db.query(sql, function(err, rows) {
      if (err) {
        res.status(500).send({ error: 'Something failed!' })
      }
      res.json({'status': 'successfully retrieved', 'data': rows});
    })
});

// route to create result
router.get('/add', function(req, res) {
 
    res.render("teacher/addstudent",{alreadyExistTrue:false,somethingFailed:false})
});
// route to create result
router.post('/add', function(req, res) {
  var rollno = req.body.rollno;
  var name = req.body.name;
  var dob = req.body.dob;
  var score = req.body.score;
  
  var sqlToCheck = "SELECT * FROM result WHERE rollno ="+rollno;

  db.query(sqlToCheck, function(err, rows) {
    if (err) {
      res.render('teacher/addstudent',{somethingFailed:true,alreadyExistTrue:false})
    }
    else if (rows && rows.length) {
      res.render('teacher/addstudent',{rollno:req.body.rollno,alreadyExistTrue:true,somethingFailed:false})
    }
    else {

      var sql = `INSERT INTO result (rollno, name, dob, score) VALUES ("${rollno}", "${name}", "${dob}", "${score}")`;
      db.query(sql, function(err) {
        if(err) {
          res.render('teacher/addstudent',{somethingFailed:true,alreadyExistTrue:false})
        }
        var sql = "SELECT * FROM result";
    db.query(sql, function(err, rows) {
      if (err) {
        res.status(500).send({ error: 'Something failed!' })
      }
      res.render('teacher/viewall', {student: rows,addedSuccesfully:true,editedSuccesfully:false,deletedSuccesfully:false,rollno:rollno});
    })

      }) 

    }
  })

 
});

// route to update result
router.get('/edit/:rollno', function(req, res) {
    var sql = "SELECT * FROM result WHERE rollno ="+[req.params.rollno];
    db.query(sql, function(err, rows) {
      if (err) {
        res.status(500).send({ error: 'Something failed!' })
      }
    rows[0].dob = moment(new Date(rows[0].dob)).format("YYYY-MM-DD");
      res.render('teacher/edit', {user: rows[0],moment: moment});
    })
});

// route to update result
router.post('/edit/:rollno', function(req, res) {
    var rollno = req.params.rollno;
    var name = req.body.name;
    var dob = req.body.dob;
    var score = req.body.score;
    var sql = `UPDATE result SET name="${name}", dob="${dob}", score="${score}" WHERE rollno=${rollno}`;
    db.query(sql, function(err) {
      if(err) {
        res.status(500).send({ error: 'Something failed!' })
      }
      var sql1 = "SELECT * FROM result";
      db.query(sql1, function(err, rows) {
      if (err) {
        res.status(500).send({ error: 'Something failed!' })
      }
      res.render('teacher/viewall', {student: rows,addedSuccesfully:false,editedSuccesfully:true,deletedSuccesfully:false,rollno:rollno});
    })
    })
  });

// route to delete result
router.post('/delete/:rollno', function(req, res) {
  var rollno = req.params.rollno;

  var sqlToCheck = "SELECT * FROM result WHERE rollno ="+rollno;
  
  db.query(sqlToCheck, function(err, rows) {
    if (err) {
      res.status(500).send({ error: 'Something failed!' })
    }
    else if (rows && rows.length) {
      var sql = `DELETE FROM result WHERE rollno=${rollno}`;
    db.query(sql, function(err) {
      if(err) {
        res.status(500).send({ error: 'Something failed!' })
      }
      
      var sql1 = "SELECT * FROM result";
      db.query(sql1, function(err, rows) {
      if (err) {
        res.status(500).send({ error: 'Something failed!' })
      }
      res.render('teacher/viewall', {student: rows,addedSuccesfully:false,editedSuccesfully:false,deletedSuccesfully:true,rollno:rollno});
    })

    })
      
    }
    else {
      res.status(409).send( {error : 'Student may not present'});
    }
  })
    
    
  })


module.exports = router;

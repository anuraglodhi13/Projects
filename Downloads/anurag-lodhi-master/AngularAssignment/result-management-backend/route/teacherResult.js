var express = require('express');
var router = express.Router();
var db = require('../db');
var bodyParser = require('body-parser');

router.use(bodyParser.json());

// route to get all details of result
router.get('/results', function(req, res) {
    var sql = "SELECT * FROM result";
    db.query(sql, function(err, rows) {
      if (err) {
        res.status(500).send({ error: 'Something failed!' })
      }
      res.json({'status': 'successfully retrieved', 'data': rows});
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
router.post('/result', function(req, res) {
 
    var rollno = req.body.rollno;
    var name = req.body.name;
    var dob = req.body.dob;
    var score = req.body.score;
    
    var sqlToCheck = "SELECT * FROM result WHERE rollno ="+rollno;

    db.query(sqlToCheck, function(err, rows) {
      if (err) {
        res.status(500).send({ error: 'Something failed!' })
      }
      else if (rows && rows.length) {
        res.status(403).send( {error : 'Student already present'});
      }
      else {

        var sql = `INSERT INTO result (rollno, name, dob, score) VALUES ("${rollno}", "${name}", "${dob}", "${score}")`;
        db.query(sql, function(err) {
          if(err) {
            res.status(500).send({ error: 'Something failed!' })
          }
          res.json({'status': 'successfully created', rollno: rollno, name: name, dob: dob, score:score})
        }) 

      }
    })

   
});

// route to update result
router.put('/result/:rollno', function(req, res) {
 
    var rollno = req.params.rollno;
    var name = req.body.name;
    var dob = req.body.dob;
    var score = req.body.score;
  
    var sql = `UPDATE result SET name="${name}", dob="${dob}", score="${score}" WHERE rollno=${rollno}`;
    db.query(sql, function(err) {
      if(err) {
        res.status(500).send({ error: 'Something failed!' })
      }
      res.json({'status': 'successfully updated', name: name, dob: dob, score: score})
    })
  });

// route to delete result
router.delete('/result/:rollno', function(req, res) {
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
      res.json({'status': 'successfully deleted', rollno: rollno})
    })
      
    }
    else {
      res.status(409).send( {error : 'Student may not present'});
    }
  })
    
    
  })


module.exports = router;
var express = require('express');
var router = express.Router();
var db = require('../db');
var bodyParser = require('body-parser');
router.use(bodyParser.json());

// route to get all details of result
router.get('/findResult', function(req, res) {
    var sql = "SELECT * FROM result";
    db.query(sql, function(err, rows) {
      if (err) {
        res.status(500).send({ error: 'Something failed!' })
      }
      res.render('student/findResult', {notFound:false});
    })
});

router.post('/findResult', function(req, res) {
    var rollno = req.body.rollno;
    var name = req.body.name;
    // var sql = "SELECT * FROM result";
    var sql = 'SELECT * FROM result WHERE rollno = ? AND name = ?';
    
    db.query(sql, [rollno, name], function (err, result) {
        if (err) {
            res.status(500).send({ error: 'Something failed!' })
          }
        else if(result.length === 0) {
            // res.status(403).send({error: 'Not found!'});
            res.render("student/findResult",{notFound:true});
        } 
        else {
            var sql = "SELECT * FROM result WHERE rollno ="+rollno;
            db.query(sql, function(err, rows) {
            if (err) {
            res.status(500).send({ error: 'Something failed!' })
            }
            res.render("student/view",{student:rows[0]});
            })
        }
    });
});


module.exports = router;

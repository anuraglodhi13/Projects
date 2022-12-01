var express = require('express');
var router = express.Router();
var db = require('../db');
var bodyParser = require('body-parser');
router.use(bodyParser.json());

// route to get details of particular result for student
router.get('/result/:rollno/:dob', function(req, res) {
    var rollno = req.params.rollno;
    var dob = req.params.dob;
    console
    var sql = 'SELECT * FROM result WHERE rollno = ? AND dob = ?';
    
    db.query(sql, [rollno, dob], function (err, result) {
        if (err) {
            res.status(500).send({ error: 'Something failed!' })
          }
        else if(result.length === 0) {
            res.status(403).send({error: 'Not found!'}); // no body can be send with 204(no content)
        } 
        else {
        res.json({'status': 'success',result});
        }
    });
});

module.exports = router;
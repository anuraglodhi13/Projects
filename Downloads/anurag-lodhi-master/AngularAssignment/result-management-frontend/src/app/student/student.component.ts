import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from '../model/student';
import { DataTransferService } from '../service/data-service';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})

export class StudentComponent implements OnInit{
  noData: boolean = false;
  studentModel = new Student;
  constructor (
    private httpClient : HttpClient,
    private router : Router,
    private dataTransferService : DataTransferService
  ) {}

  ngOnInit(): void {

    // session storage to prevent direct access of student page from url
    if(sessionStorage.getItem('id') === 'teacher') {
      this.router.navigate(['']);
    }
    sessionStorage.setItem('id','student');
  };

  onSubmit(){
    if(Boolean(this.studentModel.dob) && Boolean(this.studentModel.rollno)) {
    this.httpClient.get<any>('http://localhost:9000/student/result/'+
    this.studentModel.rollno+'/'+this.studentModel.dob).subscribe(response => {
      this.dataTransferService.studentResult = response.result[0];
      this.router.navigate(['student/result']);
      
    },
    error => {
      if(error.status == 403) {
        this.noData = true;
      }
    } );
  }
  }
  
}

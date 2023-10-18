import { HttpClient } from '@angular/common/http';
import { Component, OnInit, } from '@angular/core';
import { FormGroup  } from '@angular/forms';
import { Router } from '@angular/router';
import { Student } from '../model/student';
import { DataTransferService } from '../service/data-service';

@Component({
  selector: 'app-teacher-add',
  templateUrl: './teacher-add.component.html',
  styleUrls: ['./teacher-add.component.css']
})


export class TeacherAddComponent implements OnInit{
  rollno!: number;
  name: string = '';
  dob: string = '';
  score: string = '';
  alreadyPresent: boolean = false;
  submitted:boolean =false;
  studentModel = new Student;
  constructor (
    private httpClient : HttpClient,
    private router : Router,
    private dataTransferService : DataTransferService,
  ) {}

  ngOnInit(): void {
    // to prevent direct access of result page of student from url
    if(sessionStorage.getItem('id') !== 'teacher') {
      this.router.navigate(['']);
    }
  }

  public onSubmit() {
    // check if student model is not empty and null
    const studentNotEmpty = Object.values(this.studentModel).includes('');
    const studentNotNull = Object.values(this.studentModel).includes(null);
    
    if(!studentNotNull && !studentNotEmpty) {
    this.httpClient.post('http://localhost:9000/teacher/result',this.studentModel).subscribe(
      response => {
        console.log(response);
        this.router.navigate(['teacher']);
        this.dataTransferService.addSuccess = true;
        this.dataTransferService.editSuccess = false;
        this.dataTransferService.addSuccessRollNo = this.studentModel.rollno;
      },
      error => {
        if(error.status == 403) {
          this.alreadyPresent = true;
        }
      } 
    );
    }
    
     
  }

  onBack (){
    this.router.navigate(['teacher']);
    this.dataTransferService.addSuccess = false;
    this.dataTransferService.editSuccess = false;
  }

}

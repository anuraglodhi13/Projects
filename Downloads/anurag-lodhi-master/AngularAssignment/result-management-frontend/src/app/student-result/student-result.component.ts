import { Component, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { Student } from '../model/student';
import { DataTransferService } from '../service/data-service';

@Component({
  selector: 'app-student-result',
  templateUrl: './student-result.component.html',
  styleUrls: ['./student-result.component.css']
})
export class StudentResultComponent implements OnInit{
  rollno!: number;
  name: string = '';
  dob: string = '';
  score: string = '';
  studentModel = new Student;
  constructor (
    private router : Router,
    public dataTransferService : DataTransferService
  ) {}
  ngOnInit(): void {
    // to prevent direct access of result page of student from url
    if(sessionStorage.getItem('id') !== 'student') {
      this.router.navigate(['']);
    }
    this.studentModel = this.dataTransferService.studentResult;
    if(this.dataTransferService.studentResult === null || this.studentModel === undefined) {
      this.router.navigate(['student']);
    }
  };
  onBack (){
    this.router.navigate(['student']);
  }
}

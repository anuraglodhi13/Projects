import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataTransferService } from '../service/data-service';

export class Result {
  constructor (
    public rollno: number,
    public name: string,
    public dob: string,
    public score: string,
  ) {

  }
}

@Component({
  selector: 'app-teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.css']
})


export class TeacherComponent implements OnInit {
  results: Result [] = [];
  rollno!: number;
  displayStyle = "none";
  deleteSuccess:boolean = false;
  addSuccess:boolean = false;
  editSuccess:boolean = false;
  addSuccessRollNo!:number;
  editSuccessRollNo!:number;
  constructor (
    private httpClient : HttpClient,
    public dataService: DataTransferService,
    private router : Router,
  ) {

  }

  ngOnInit() :void{
   this.getResults();
   this.addSuccess = this.dataService.addSuccess;
   this.addSuccessRollNo = this.dataService.addSuccessRollNo;
   this.editSuccess = this.dataService.editSuccess;
   this.editSuccessRollNo = this.dataService.editSuccessRollNo;
   // session storage to prevent direct access of teacher page from url
   if(sessionStorage.getItem('id') === 'student') {
    this.router.navigate(['']);
  }
  sessionStorage.setItem('id','teacher');
  }

  getResults () {
    this.httpClient.get<any>('http://localhost:9000/teacher/results').subscribe(
        response => {
          this.results = response.data;
        }
    );
  }

  openFormModal(rollno : number) {
    this.rollno = rollno;
    this.displayStyle = "block";
  }
  closePopup() {
        this.displayStyle = "none";
  }
  deleteRecord() {
    this.httpClient.delete('http://localhost:9000/teacher/result/'+this.rollno).subscribe(data => {
      this.getResults();
      this.deleteSuccess=true;
    });
    this.addSuccess = false;
    this.editSuccess = false;
    this.displayStyle = "none";
  }
  
  onLogout() {
    sessionStorage.removeItem('id');
    this.router.navigate(['']);
  }
}

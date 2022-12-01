import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Student } from '../model/student';
import { DataTransferService } from '../service/data-service';
export class ResultForUpdate {
  constructor (
    public name: string,
    public dob: string,
    public score: string,
  ) {

  }
}

@Component({
  selector: 'app-teacher-edit',
  templateUrl: './teacher-edit.component.html',
  styleUrls: ['./teacher-edit.component.css']
})
export class TeacherEditComponent implements OnInit{
  rollno!: number;
  name: string = '';
  dob: string = '';
  score: string = '';
  editSuccess: boolean = false;
  studentModel = new Student;
  constructor (
    private httpClient : HttpClient,
    private activatedRoute: ActivatedRoute,
    private router : Router,
    private dataTransferService : DataTransferService
  ) {}

ngOnInit(): void {
  if(sessionStorage.getItem('id') !== 'teacher') {
    this.router.navigate(['']);
  }
  this.activatedRoute.params.subscribe(paramsId => {
    this.rollno = paramsId['id'];
});

this.httpClient.get<any>('http://localhost:9000/teacher/result/'+this.rollno).subscribe(
      response => {
        this.studentModel.rollno = this.rollno;
        this.studentModel.name = response.data[0].name;
        this.studentModel.dob = response.data[0].dob;
        this.studentModel.score = response.data[0].score;
      }
  );

}

onSubmit(){
  // check if student model is not empty and null
  const studentNotEmpty = Object.values(this.studentModel).includes('');
  const studentNotNull = Object.values(this.studentModel).includes(null);

  if(!studentNotNull && !studentNotEmpty) {
  this.httpClient.put('http://localhost:9000/teacher/result/'+this.rollno,this.studentModel).subscribe(
      response => {
        this.router.navigate(['teacher']);
        this.dataTransferService.editSuccess = true;
        this.dataTransferService.addSuccess = false;
        this.dataTransferService.editSuccessRollNo = this.rollno;
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

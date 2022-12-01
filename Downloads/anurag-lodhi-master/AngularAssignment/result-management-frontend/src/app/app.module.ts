import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { StudentComponent } from './student/student.component';
import { AppRoutingModule } from './app-routing.module';
import { MenuComponent } from './menu/menu.component';
import { TeacherComponent } from './teacher/teacher.component';
import { TeacherEditComponent } from './teacher-edit/teacher-edit.component';
import { TeacherAddComponent } from './teacher-add/teacher-add.component';
import { FormsModule } from '@angular/forms';
import { DataTransferService } from './service/data-service';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { StudentResultComponent } from './student-result/student-result.component';
@NgModule({
  declarations: [
    AppComponent,
    StudentComponent,
    MenuComponent,
    TeacherComponent,
    TeacherEditComponent,
    TeacherAddComponent,
    StudentResultComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule ,
    MatDatepickerModule,
    BrowserAnimationsModule
  ],
  providers: [DataTransferService],
  bootstrap: [AppComponent]
})
export class AppModule { }

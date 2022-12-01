import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { StudentResultComponent } from './student-result/student-result.component';
import { StudentComponent } from './student/student.component';
import { TeacherAddComponent } from './teacher-add/teacher-add.component';
import { TeacherEditComponent } from './teacher-edit/teacher-edit.component';
import { TeacherComponent } from './teacher/teacher.component';

const routes: Routes = [
  {
    path: '',
    component: AppComponent,

    children: [
      {
        path: '',
        component: MenuComponent
      },
      {
        path: 'teacher',
        component: TeacherComponent
      },
      {
        path: 'teacher/edit/:id',
        component: TeacherEditComponent
      },
      {
        path: 'teacher/add',
        component: TeacherAddComponent
      },
      {
        path: 'student',
        component: StudentComponent
      },
      {
        path: 'student/result',
        component: StudentResultComponent
      }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
  ],
  exports: [
    RouterModule
  ],
  providers: []
})
export class AppRoutingModule { }

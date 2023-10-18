import { Injectable } from "@angular/core";
import { Student } from "../model/student";

Injectable({
    providedIn:"root"
})
export class DataTransferService {
    constructor() {}
    public addSuccess : boolean = false;
    public addSuccessRollNo! : number;
    public editSuccess : boolean = false;
    public editSuccessRollNo! : number;
    public studentResult!: Student;
}
import { Component, OnInit } from '@angular/core';
import { DataTransferService } from '../service/data-service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit{
  constructor (private dataTransferService : DataTransferService) {}
  ngOnInit(): void {
    sessionStorage.removeItem('id');
    this.dataTransferService.addSuccess = false;
    this.dataTransferService.editSuccess = false;
  }

}

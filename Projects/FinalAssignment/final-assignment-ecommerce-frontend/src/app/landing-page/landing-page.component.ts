import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../service/authentication-service';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent implements OnInit{
  constructor(
    private authenticationService: AuthenticationService,
  ) {} 
  ngOnInit(): void {
    this.authenticationService.logout();
  }

}

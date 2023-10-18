import { Component, Injectable, OnInit } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class Constants {

  public readonly DOMAIN: string = 'http://localhost:8080/';
  
}

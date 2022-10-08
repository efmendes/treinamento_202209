import { Router } from '@angular/router';
import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user?: string;

  constructor(private loginService: LoginService,
    private router: Router,
    private location: Location) {
  }

  ngOnInit(): void {
  }

  isPresent(){
    let userPresent = this.loginService.username;
    this.user = userPresent;
    return userPresent ? true: false;
  }

  logOut(){
    this.loginService.logOut();
    this.user = '';
    this.isPresent();
    this.router.navigateByUrl('');
  }


}

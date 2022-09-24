import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user: string | undefined;
  constructor(private loginService: LoginService) {
  this.user = this.loginService.username;

  }

  ngOnInit(): void {
  }

  isPresent(){
    if(this.user){
      return true;
    } else{
      return false;
    }
  }


}

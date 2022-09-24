import { NonNullableFormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private formBuilder: NonNullableFormBuilder,
    private loginService: LoginService) { }

  ngOnInit(): void {
  }

  form = this.formBuilder.group({
    username: [''],
    password: ['']
  });
  user = this.form.value.username;
  pass = this.form.value.password;
  onLogin(){
    this.loginService.authenticate(this.form.value).subscribe(
      ()=> this.loginService.setData(this.user, this.pass)
      );

  }

}

import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private formBuilder: NonNullableFormBuilder,
    private loginService: LoginService,
    private location: Location) { }

  ngOnInit(): void {
  }

  form = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });
  user = this.form.value.username;
  pass = this.form.value.password;
  onLogin(){
    this.loginService.authenticate(this.form.value).subscribe();
    this.loginService.setData(this.form.value);
    this.location.back();
  }

}

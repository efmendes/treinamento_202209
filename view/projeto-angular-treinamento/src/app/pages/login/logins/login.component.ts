import { ActivatedRoute, Router } from '@angular/router';
import { ICliente } from 'src/app/interfaces/cliente';
import { Login } from 'src/app/interfaces/login';
import { ClientesService } from 'src/app/services/clientes.service';
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
    private router: Router,
    private location: Location) { }

  ngOnInit(): void {
  }

  form = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });

  onLogin(){
    const login = this.form.value as Login;
    this.loginService.authenticate(login).subscribe((login: Login) => {
      this.loginService.setData(login);
    });
    this.router.navigate(['/']);
  }

}

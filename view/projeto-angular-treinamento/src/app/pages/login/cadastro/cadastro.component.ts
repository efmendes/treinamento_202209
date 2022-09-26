import { LoginService } from 'src/app/services/login.service';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  constructor(private formBuilder: NonNullableFormBuilder,
    private loginService: LoginService,
    private location: Location) { }

  ngOnInit(): void {
  }

  form = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });

  onCadastro(){
    this.loginService.cadastro(this.form.value).subscribe();
    this.location.back();
  }

}

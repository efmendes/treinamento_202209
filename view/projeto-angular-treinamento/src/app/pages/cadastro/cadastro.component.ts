import { LoginService } from 'src/app/services/login.service';
import { NonNullableFormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  constructor(private formBuilder: NonNullableFormBuilder,
    private loginService: LoginService) { }

  ngOnInit(): void {
  }

  form = this.formBuilder.group({
    username: [''],
    password: ['']
  });

  onCadastro(){
     this.loginService.cadastro(this.form.value);
  }

}

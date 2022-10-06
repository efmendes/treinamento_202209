import { LocalStorageService } from './../../../services/local-storage.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ICliente } from './../../../interfaces/cliente';
import { LoginService } from 'src/app/services/login.service';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Login } from 'src/app/interfaces/login';
import { ClientesService } from 'src/app/services/clientes.service';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  idCliente = 0;
  cliente?: ICliente;

  constructor(private formBuilder: NonNullableFormBuilder,
    private loginService: LoginService,
    private clienteService: ClientesService,
    private localStorage: LocalStorageService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.idCliente = Number(this.route.snapshot.paramMap.get('cpf'));
    this.clienteService.buscarClientePorCpf(String(this.idCliente)).subscribe((cliente: ICliente) => {
      this.cliente = cliente
    })
  }

  formLogin = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
  });

  onCadastro(){
    let login: Login = this.formLogin.value as Login;
    login.cliente = this.cliente;

    console.log(login)
    this.loginService.cadastro(login).subscribe(()=> {
      this.localStorage.set('username', login.username);
      this.localStorage.set('password', login.password);
      this.localStorage.set('cpf', String(login.cliente?.cpf));
    });


    this.router.navigate(['login']);

  }

}

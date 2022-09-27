import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MensagemService} from "../../services/mensagem.service";
import {ClienteService} from "../../services/cliente.service";
import {ICliente} from "../../interfaces/cliente";

@Component({
  selector: 'app-cadastro-usuario',
  templateUrl: './cadastro-usuario.component.html',
  styleUrls: ['./cadastro-usuario.component.css']
})
export class CadastroUsuarioComponent implements OnInit {

  emptyCliente: ICliente = {
    nome: '',
    cpf: '',
    id: 0,
    email: '',
    observacoes: '',
    ativo: true
  }
  formGroup: FormGroup = this.preencheFormGroup(this.emptyCliente);
  constructor(private  formBuilder: FormBuilder, private mensagemService: MensagemService, private clienteService: ClienteService) {

  }

  ngOnInit(): void {
  }
  preencheFormGroup(cliente: ICliente): FormGroup {
    return new FormGroup({
      id: new FormControl(cliente.id ? cliente.id : null),
      nome: new FormControl(cliente.nome, Validators.required),
      cpf: new FormControl(cliente.cpf, Validators.required),
      email: new FormControl(cliente.email, [
        Validators.required,
        Validators.email,
      ]),
      observacoes: new FormControl(cliente.observacoes),
      ativo: new FormControl(cliente.ativo),
    });
  }


  cadastrarCliente(){
    const cliente: ICliente = this.formGroup.value;
    this.clienteService.CadastrarUmCLiente(cliente).subscribe({
      next:()=> this.mensagemService.success("Cliente Cadastrado com sucesso"),
      error:() => this.mensagemService.error("Erro ao cadastrar cliente")

    })

  }


}

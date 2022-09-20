import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MensagemService} from "../../services/mensagem.service";

@Component({
  selector: 'app-cadastro-usuario',
  templateUrl: './cadastro-usuario.component.html',
  styleUrls: ['./cadastro-usuario.component.css']
})
export class CadastroUsuarioComponent implements OnInit {
  formCadastro: FormGroup
  constructor(private  formBuilder: FormBuilder, private mensagemService: MensagemService) {
    this.formCadastro = formBuilder.group({
      nome: ['',Validators.compose([Validators.required])],
      email:['',Validators.compose([Validators.required,Validators.email])],
      cpf:['',Validators.compose([Validators.required])],
      ativo:[true,Validators.compose([Validators.pattern('true')])]
    })
  }

  ngOnInit(): void {
  }

  private cpfValid(){
    const cpfRegex = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})"
    const cpf = this.formCadastro.value.cpf.match(cpfRegex)
    return cpf
  }

  cadastrarCliente(){
    if(this.formIsValid()){
      console.log(`${this.formCadastro.value.nome} - ${this.formCadastro.value.cpf}`)
    }

  }

  private formIsValid():boolean{
    if(!this.formCadastro.valid){
      this.mensagemService.error('Formulário Inválido')
      return false
    }else if(!this.cpfValid()){
      this.mensagemService.warning('CPF inválido')
      return false
    }else{
      this.mensagemService.success("Cliente Cadastrado com sucesso")
      return true
    }

  }
}

import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ClienteContaResponse, ICliente } from 'src/app/interfaces/cliente';

import { IConta, IContaCreatedResponse } from 'src/app/interfaces/conta';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { ContasService } from 'src/app/services/contas.service';
import { ClientesService } from 'src/app/services/clientes.service';

@Component({
  selector: 'app-contas-cadastrar-editar',
  templateUrl: './contas-cadastrar-editar.component.html',
  styleUrls: ['./contas-cadastrar-editar.component.css']
})
export class ContasCadastrarEditarComponent implements OnInit {

  cliente: any;
  hasCliente: boolean = false;
  hasIdEdit: boolean = false;
  hasConta: boolean = false;
  idCliente: string = '';
  urlEditar: boolean = false;
  resultado: any;
  // emptyContas: IConta={
  //   id:0,
  //   agencia: '',
  //   numero: '',
  //   saldo: 0,
  //   cliente: this.cliente
  // }

  formConta: FormGroup = this.preencheFormGroup();
  formCliente: FormGroup = this.preencheFormCliente();
  formCriarConta: FormGroup = this.preencheCriarConta();

  constructor(private contasService: ContasService, private clientesService: ClientesService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    this.urlEditar = !(this.router.url === "/contas/editar/"+id)
    if (this.router.url === "/contas/editar/"+id){
      if (id){
        this.contasService.buscarPorIdString(id).subscribe((result: IConta) =>{
          this.formConta = this.preencheFormGroup(result.cliente?.cpf);
          this.buscarClienteCpf();
          this.exibirCriarConta();
          this.preencheCriarConta(result);
          this.resultado = result;
          this.hasIdEdit = true;
        }, error =>{
          console.error(error);
        })
      }
    }else if (id){
      this.clientesService.buscarPorId(id).subscribe((result: ICliente )=>{
        this.formConta = this.preencheFormGroup(result.cpf);
        this.buscarClienteCpf();
      }, error => {
        console.error(error);
      });
    }
  }

  buscarClienteCpf(){
    return this.clientesService.buscarClienteCpf(this.formConta.get('cpf')?.value).subscribe((res) => {
      this.cliente = res;
      if (this.cliente){
        this.hasCliente = true;
      }
      this.preencheFormCliente(res);
      this.formCliente.disabled;
      this.idCliente = this.cliente.id;
      console.log(this.idCliente);
    }, error => {
      Swal.fire('Erro!', `CPF Inválido ou incorreto`, 'error');
      console.error(error);
    });
  }

  limpar(){
    this.formConta.reset();
    this.hasCliente = false;
    this.hasConta = false;
  }

  exibirCriarConta(){
    if (this.hasConta){
      this.hasConta = false;
      return
    }
    this.hasConta = true
  }
  // preencheFormGroupTeste(cpf: string){
  //   return this.formConta= new FormGroup({
  //     cpf: new FormControl(cpf)
  //   })
  // }

  preencheFormGroup(cpf?: string){
    return this.formConta= new FormGroup({
      cpf: new FormControl(null || cpf)
    })
  }

  preencheFormCliente(cliente?: any){
    return this.formCliente = new FormGroup({
      nome: new FormControl({value: this.cliente?.nome, disabled: true}),
      email: new FormControl({value: this.cliente?.email, disabled: true}),
      id: new FormControl({value: this.cliente?.id, disabled: true}),
    })
  }

  preencheCriarConta(conta?: IConta){
    return this.formCriarConta = new FormGroup({
      agencia: new FormControl(conta?.agencia || null, Validators.required),
      numeroConta: new FormControl(conta?.numero || null, Validators.required),
      saldo: new FormControl(conta?.saldo || null, Validators.required),
      idCliente: new FormControl(this.idCliente, Validators.required)
    })
  }

  montarPayLoad(form: FormGroup):IContaCreatedResponse{
    const cliente = {
      id: this.idCliente
    }
    const conta = {
      agencia: this.formCriarConta.get('agencia')?.value,
      numero: this.formCriarConta.get('numeroConta')?.value,
      saldo: this.formCriarConta.get('saldo')?.value,
      cliente: cliente
    }
    return conta;
  }

  editarConta(res: IContaCreatedResponse){
    const conta = this.montarPayLoad(this.formCriarConta);
    conta.id = res.id
    this.contasService.editarConta(conta).subscribe(result => {
      Swal.fire('Sucesso!!', `Editado com sucesso!`, 'success' );
      this.router.navigate(['/contas']);
    }, error => {
      Swal.fire('Erro!', `Houve um erro na edição da conta`, 'error');
      console.error(error);
    });
  }

  enviar(){
    const conta = this.montarPayLoad(this.formCriarConta.value);
    this.contasService.cadastrarEditar(conta).subscribe(result => {
      Swal.fire('Sucesso!!', `Cadastrado com sucesso!`, 'success' );
      this.router.navigate(['/contas']);
    }, error => {
      Swal.fire('Erro!', `Houve um erro na criação da conta`, 'error');
      console.error(error);
    });
  }

  estaEditando(){
    return !!this.formConta.get("id")?.value;
  }
}

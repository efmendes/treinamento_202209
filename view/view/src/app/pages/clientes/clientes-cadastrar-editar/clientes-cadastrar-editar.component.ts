import { ValueConverter } from '@angular/compiler/src/render3/view/template';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ICliente } from 'src/app/interfaces/cliente';
import { ClientesService } from 'src/app/services/clientes.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-clientes-cadastrar-editar',
  templateUrl: './clientes-cadastrar-editar.component.html',
  styleUrls: ['./clientes-cadastrar-editar.component.css']
})
export class ClientesCadastrarEditarComponent implements OnInit {

  hasIdEdit: boolean = false;

  emptyCliente: ICliente={
    id: 0,
    nome: '',
    cpf: '',
    email: '',
    observacoes: '',
    ativo: true
  }

  formCliente: FormGroup = this.preencheFormGroup(this.emptyCliente);

  constructor(private clientesService: ClientesService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    if (id){
      this.hasIdEdit = true;
      this.clientesService.buscarPorId(id).subscribe((result: ICliente)=>{
        this.formCliente = this.preencheFormGroup(result);

      }, error => {
        console.error(error);
      });
    }
  }

  preencheFormGroup(cliente: ICliente){
    return this.formCliente= new FormGroup({
      id: new FormControl(cliente.id ? cliente.id: null),
      nome: new FormControl(cliente.nome, Validators.required),
      cpf: new FormControl(cliente.cpf, Validators.required),
      email: new FormControl(cliente.email, [Validators.required, Validators.email]),
      observacoes: new FormControl(cliente.observacoes),
      ativo: new FormControl(cliente.ativo)
    })
  }

  enviar(){
    const cliente: ICliente = this.formCliente.value;
    this.clientesService.cadastrarEditar(cliente).subscribe(result => {
      Swal.fire('Sucesso!!', `${this.estaEditando() ? 'Editado' : 'Cadastrado'} com sucesso!`, 'success' );
      this.router.navigate(['/clientes']);
    },error =>{
      Swal.fire('Erro ao cadastrar', 'Verifique os campos', 'error');
    });
  }

  estaEditando(){
    return !!this.formCliente.get("id")?.value;
  }

}

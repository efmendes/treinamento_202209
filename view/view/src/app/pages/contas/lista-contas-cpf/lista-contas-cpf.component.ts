import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ClienteContaResponse } from 'src/app/interfaces/cliente';
import { IConta } from 'src/app/interfaces/conta';
import { ClientesService } from 'src/app/services/clientes.service';
import { ContasService } from 'src/app/services/contas.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-lista-contas-cpf',
  templateUrl: './lista-contas-cpf.component.html',
  styleUrls: ['./lista-contas-cpf.component.css']
})
export class ListaContasCpfComponent implements OnInit {

  constructor(private contasService: ContasService, private router: Router, private activatedRoute: ActivatedRoute, private clientesService: ClientesService) { }
  contas: IConta[] = [];
  ngOnInit(): void {
    const cpf = this.activatedRoute.snapshot.paramMap.get('cpf');
    if(cpf){
      this.listarContasCpf(cpf);
    }
  }

  listarContasCpf(cpf:string){
    this.contasService.buscarContaPorCpf(cpf).subscribe((result: IConta[]) => {
      this.contas = result;
      if(this.contas == null){
        Swal.fire('Erro no listar conta', 'Esse cliente não possui conta', 'error');
        history.back()
      }
    },error =>{
      Swal.fire('Erro ao consultar', 'Esse cliente não existe ou não possui conta', 'error');
      history.back()
    });
  }

  confirmar(id: any){
    Swal.fire({ title: 'Você tem certeza?', text: "Você não pode reverter isto", icon: 'warning', showCancelButton: true, confirmButtonColor: '#3085d6', cancelButtonColor: '#d33',cancelButtonText:"Cancelar", confirmButtonText: 'Sim, remova!' }).then((result) => { if (result.isConfirmed) { this.contasService.removerConta(id).subscribe(result =>{
      Swal.fire( 'Removido!', 'Conta deletado com sucesso', 'success');
      window.location.reload()
      this.listarContasCpf(id)
    }, error => {
      console.error(error);
    });  } })
  }

}

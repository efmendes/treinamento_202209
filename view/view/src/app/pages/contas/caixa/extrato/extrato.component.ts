import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { IConta } from 'src/app/interfaces/conta';
import { IExtrato } from 'src/app/interfaces/extrato';
import { ContasService } from 'src/app/services/contas.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-extrato',
  templateUrl: './extrato.component.html',
  styleUrls: ['./extrato.component.css']
})
export class ExtratoComponent implements OnInit {

  constructor(private contasService: ContasService, private router: Router, private activatedRoute: ActivatedRoute) { }
  emptyContas: IConta={
    id:0,
    agencia: '',
    numero: '',
    saldo: 0,
    cliente: {
      id: 0,
      nome: '',
      cpf: '',
      email: '',
      observacoes: '',
      ativo: true
    }
  }
  data = new Date();
  extratos: IExtrato[] = [];
  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    if(id){
      this.contasService.buscarPorIdString(id).subscribe((result: IConta) =>{
        this.consultarExtrato (result.agencia, result.numero);
        this.emptyContas = result
      })
    }
  }


  consultarExtrato(agencia: string, conta:string){
    this.contasService.consultaExtrato(agencia, conta).subscribe((res: any) =>{
      this.extratos = res
      if(res.length == 0 ){
        Swal.fire('Erro no extrato', 'Essa conta não possui extrato', 'error');
      }
    });
  }

}
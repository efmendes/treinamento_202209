import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { IConta } from 'src/app/interfaces/conta';
import { IExtrato } from 'src/app/interfaces/extrato';
import { ContasService } from 'src/app/services/contas.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-extrato-agencia-conta',
  templateUrl: './extrato-agencia-conta.component.html',
  styleUrls: ['./extrato-agencia-conta.component.css']
})
export class ExtratoAgenciaContaComponent implements OnInit {

  constructor(private contasService: ContasService, private router: Router, private activatedRoute: ActivatedRoute) { }

  extratos: IExtrato[] = [];
  ngOnInit(): void {
    const agencia = this.activatedRoute.snapshot.paramMap.get('agencia');
    const conta = this.activatedRoute.snapshot.paramMap.get('conta');
    if(conta && agencia){
      this.consultarExtrato(agencia, conta);
    }
  }


  consultarExtrato(agencia: string, conta:string){
    this.contasService.consultaExtrato(agencia, conta).subscribe((res: any) =>{
      this.extratos = res
    },error =>{
      Swal.fire('Erro ao consultar', 'Essa conta não existe ou não possui extrato', 'error');
      this.router.navigate(['/'])
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IConta } from 'src/app/interfaces/conta';
import { ContasService } from 'src/app/services/contas.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-contas',
  templateUrl: './contas.component.html',
  styleUrls: ['./contas.component.css']
})
export class ContasComponent implements OnInit {

  constructor(private contasService: ContasService, private router: Router, private activatedRoute: ActivatedRoute) { }
  contas: IConta[] = [];
  ngOnInit(): void {
    this.listarContas();
  }

  listarContas(){
    this.contasService.listarTodasContas().subscribe((result: any) => {
      this.contas = result;
    });
  }

  confirmar(id: any){
    Swal.fire({ title: 'Você tem certeza?', text: "Você não pode reverter isto", icon: 'warning', showCancelButton: true, confirmButtonColor: '#3085d6', cancelButtonColor: '#d33',cancelButtonText:"Cancelar", confirmButtonText: 'Sim, remova!' }).then((result) => { if (result.isConfirmed) { this.contasService.removerConta(id).subscribe(result =>{
      Swal.fire( 'Removido!', 'Conta deletado com sucesso', 'success');
      this.listarContas();
    }, error => {
      console.error(error);
    });  } })
  }
}

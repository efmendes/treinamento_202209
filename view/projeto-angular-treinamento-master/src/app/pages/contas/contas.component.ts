import { AlertasService } from 'src/app/services/alertas.service';
import { Component, OnInit } from '@angular/core';
import { IConta } from 'src/app/interfaces/conta';
import { ContasService } from 'src/app/services/contas.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-contas',
  templateUrl: './contas.component.html',
  styleUrls: ['./contas.component.css']
})
export class ContasComponent implements OnInit {

  constructor(private contasService: ContasService, private alertaService: AlertasService) { }
  contas: IConta[] = [];
  ngOnInit(): void {
    this.buscarTodasContas();
  }

  buscarTodasContas() {
    this.contasService.listarTodasContas().subscribe((contas: IConta[]) => {
      this.contas = contas;
    });
  }

  excluir(id?: number) {
    Swal.fire({
      title: 'Tem certeza?',
      text: "Essa ação não poderá ser desfeita!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sim, pode apagar!'
    }).then((result) => {
      if (result.isConfirmed) {

        if (id) {
          this.contasService.excluirContaPorId(id).subscribe(() => {
            this.buscarTodasContas();
          }, (error) => {
            console.error(error);
            this.alertaService.alertaErro("Não é possivel deletar essa conta!");
          });
        }

        Swal.fire(
          'Deletada!',
          'Conta deletada com sucesso.',
          'success'
        );
      } else {
        Swal.close;
      }
    });
  }

}

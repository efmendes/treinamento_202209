import { Component, OnInit } from '@angular/core';
import { IConta } from 'src/app/interfaces/conta';
import { MatDialog } from '@angular/material/dialog';
import { ContasService } from 'src/app/services/contas.service';
import Swal from 'sweetalert2';
import { ExtratoComponent } from '../extrato/extrato.component';

@Component({
  selector: 'app-contas',
  templateUrl: './contas.component.html',
  styleUrls: ['./contas.component.css']
})
export class ContasComponent implements OnInit {

  constructor(private contaService: ContasService, private dialogRef: MatDialog) { }
  contas: IConta[] = [];
  loading = false;
  paginaAtual = 1;

  ngOnInit(): void {
    this.buscarTodasContas();
  }

  buscarTodasContas() {
    this.loading = true;
    this.contaService.listarTodasContas().subscribe((contas: IConta[]) => {
      this.loading = false;
      this.contas = contas;
    });
  }

  openModal(nome: string, agencia: string, numeroConta: string) {
    this.dialogRef.open(ExtratoComponent, {
      data: {
        nome: nome,
        agencia: agencia,
        numeroConta: numeroConta,
      },
    });
  }

  excluir(id: number) {
    Swal.fire({
      title: 'Tem certeza?',
      text: 'Você não conseguirá reverter!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sim, deletar!',
    }).then((result) => {
      if (result.isConfirmed) {
        this.contaService.remover(id).subscribe(
          (result) => {
            Swal.fire('Deletado!', 'A conta foi deletada.', 'success');
            this.buscarTodasContas();
          },
          (error) => {
            console.error(error);
          }
        );
      }
    });
  }

}

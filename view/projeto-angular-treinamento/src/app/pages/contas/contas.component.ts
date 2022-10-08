import { Component, OnInit } from '@angular/core';
import { IConta } from 'src/app/interfaces/conta';
import { AlertasService } from 'src/app/services/alertas.service';
import { ContasService } from 'src/app/services/contas.service';

@Component({
  selector: 'app-contas',
  templateUrl: './contas.component.html',
  styleUrls: [
    './contas.component.css',
    '../pages.css',
    '../../app.component.css',
  ],
})
export class ContasComponent implements OnInit {
  constructor(
    private contasService: ContasService,
    private alertasService: AlertasService
  ) {}

  contas: IConta[] = [];
  listingError = false;

  ngOnInit(): void {
    this.buscarContas();
  }

  buscarContas() {
    this.contasService.listarContas().subscribe(
      (contas: IConta[]) => {
        this.contas = contas;
      },
      (err) => {
        this.listingError = true;
      }
    );
  }

  removerConta(id: number) {
    this.contasService.apagarConta(id).subscribe(
      () =>
        this.alertasService.alertaSucess('Conta excluída com sucesso!', () => {
          this.contas = [];
          this.buscarContas();
        }),
      (err) => {
        this.alertasService.alertaFalha(
          'Não foi possível excluir esta conta no momento, tente novamente mais tarde'
        );
      }
    );
  }
}

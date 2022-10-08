import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ISaqueDeposito, ITransferencia } from '../interfaces/operacoes';

@Injectable({
  providedIn: 'root',
})
export class OperacoesService {
  endpoint = 'contas/';
  api = environment.api;

  constructor(private http: HttpClient) {}

  sacar(saque: ISaqueDeposito) {
    return this.http.put(`${this.api}/${this.endpoint}sacar`, saque);
  }

  depositar(deposito: ISaqueDeposito) {
    return this.http.put(`${this.api}/${this.endpoint}deposito`, deposito);
  }

  transferir(transferencia: ITransferencia) {
    return this.http.put(
      `${this.api}/${this.endpoint}transferencia`,
      transferencia
    );
  }
}

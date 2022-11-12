import { Transferencia } from './../interfaces/transferencia';
import { Deposito } from './../interfaces/deposito';
import { Saque } from './../interfaces/saque';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { IConta } from '../interfaces/conta';

@Injectable({
  providedIn: 'root'
})
export class ContasService {

  endpoint = 'contas/';
  api = environment.api;

  constructor(private http: HttpClient) { }

  listarTodasContas() {
    return this.http.get<IConta[]>(`${this.api}/${this.endpoint}`);
  }

  buscarContaPorId(id: number) {
    return this.http.get<IConta>(`${this.api}/${this.endpoint}${id}`);
  }

  buscarContaPorCpf(cpf: string) {
    return this.http.get<IConta[]>(`${this.api}/${this.endpoint}/buscarContasDoCliente/${cpf}`);
  }

  cadastrarConta(conta: IConta) {
    return this.http.post(`${this.api}/${this.endpoint}`, conta);
  }

  atualizarConta(conta: IConta) {
    return this.http.put(`${this.api}/${this.endpoint}${conta.id}`, conta);
  }

  excluirContaPorId(id: number) {
    return this.http.delete(`${this.api}/${this.endpoint}${id}`)
  }

  sacar(saque: Saque) {
    return this.http.put(`${this.api}/${this.endpoint}sacar/`, saque);
  }

  depositar(deposito: Deposito) {
    return this.http.put(`${this.api}/${this.endpoint}deposito/`, deposito);
  }

  transferencia(transferencia: Transferencia) {
    return this.http.put(`${this.api}/${this.endpoint}transferencia/`, transferencia);
  }
}

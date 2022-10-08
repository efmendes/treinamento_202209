import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { IConta } from '../interfaces/conta';
import { IOperacao } from '../interfaces/operacoes';

@Injectable({
  providedIn: 'root',
})
export class ContasService {
  endpoint = 'contas/';
  api = environment.api;

  constructor(private http: HttpClient) {}

  listarContas() {
    return this.http.get<IConta[]>(`${this.api}/${this.endpoint}`);
  }

  criarConta(novaConta: IConta) {
    return this.http.post(`${this.api}/${this.endpoint}`, novaConta);
  }

  editarConta(id: number, contaAtualizada: IConta) {
    return this.http.put(`${this.api}/${this.endpoint}${id}`, contaAtualizada);
  }

  apagarConta(id: number) {
    return this.http.delete(`${this.api}/${this.endpoint}${id}`);
  }

  getConta(id: number) {
    return this.http.get<IConta>(`${this.api}/${this.endpoint}${id}`);
  }

  getExtrato(
    agencia: string,
    numero: string,
    dataInicial: string,
    dataFinal: string
  ) {
    return this.http.get<IOperacao[]>(
      `${this.api}/operacoes/extrato/${agencia}/${numero}/${dataInicial}/${dataFinal}`
    );
  }
}

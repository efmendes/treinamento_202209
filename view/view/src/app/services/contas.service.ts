import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ICliente } from '../interfaces/cliente';
import { IConta, IContaCreatedResponse } from '../interfaces/conta';
import { IDepositoSaque } from '../interfaces/deposito-saque';
import { ITransferencia } from '../interfaces/transferencia';
@Injectable({
  providedIn: 'root'
})
export class ContasService {

  api = environment.api;

  endpoint = 'contas';
  constructor(private http: HttpClient) { }

  listarTodasContas(){
    return this.http.get(`${this.api}/${this.endpoint}/`)
  }

  saque(saqueDeposito: IDepositoSaque) {
    return this.http.put<IDepositoSaque>(`${this.api}/${this.endpoint}/saque`, saqueDeposito);
  }
  deposito(deposito: IDepositoSaque) {
    return this.http.put<IDepositoSaque>(`${this.api}/${this.endpoint}/deposito`, deposito);
  }
  transferencia(transferencia: ITransferencia) {
    return this.http.put(`${this.api}/${this.endpoint}/transferencia`, transferencia);
  }

  cadastrarEditar(conta: IContaCreatedResponse){
    return this.http.post(`${this.api}/${this.endpoint}/`, conta);
  }

  editarConta(conta: IContaCreatedResponse){
    return this.http.put(`${this.api}/${this.endpoint}/${conta.id}`, conta);
  }

  removerConta(id: string){
    return this.http.delete(`${this.api}/${this.endpoint}/${id}`);
  }

  buscarPorId(id: number){
    return this.http.get<IConta>(`${this.api}/${this.endpoint}/${id}`);
  }

  buscarPorIdString(id: string){
    return this.http.get<IConta>(`${this.api}/${this.endpoint}/${id}`);
  }

  buscarContaPorCpf(cpf: string){
    return this.http.get<IConta[]>(`${this.api}/${this.endpoint}/consultar-contas-por-cpf/${cpf}`);
  }

  buscarPorIdCliente(id: string){
    return this.http.get<ICliente>(`${this.api}/clientes/${id}`);
  }

  consultaExtrato(agencia: string, conta: string){
    return this.http.get(`${this.api}/${this.endpoint}/consultar-extrato/${agencia}/${conta}`);
  }


}
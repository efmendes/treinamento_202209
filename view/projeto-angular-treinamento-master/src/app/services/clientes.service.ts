import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ICliente } from '../interfaces/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  endpoint = 'clientes/';
  api = environment.api;

  constructor(private http: HttpClient) { }

  listarTodosClientes() {
    return this.http.get<ICliente[]>(`${this.api}/${this.endpoint}`);
  }

  cadastrarCliente(cliente: ICliente) {
    return this.http.post(`${this.api}/${this.endpoint}`, cliente);
  }

  atualizarCliente(cliente: ICliente) {
    return this.http.put(`${this.api}/${this.endpoint}/${cliente.id}`, cliente);
  }

  buscarClientePorId(id: number) {
    return this.http.get<ICliente>(`${this.api}/${this.endpoint}/${id}`);
  }

  buscarClientePorCpf(cpf: string) {
    return this.http.get<ICliente>(`${this.api}/${this.endpoint}/buscarPorCpf/${cpf}`);
  }

  excluirClientePorId(id: number) {
    return this.http.delete(`${this.api}/${this.endpoint}/${id}`);
  }

}

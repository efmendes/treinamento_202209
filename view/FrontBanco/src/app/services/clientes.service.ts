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

  buscarPorId(id: number) {
    return this.http.get<ICliente>(`${this.api}/${this.endpoint}/${id}`)
  }

  cadastrar(cliente: ICliente) {
    return this.http.post(`${this.api}/${this.endpoint}/`, cliente);
  }

  remover(id: number) {
    return this.http.delete(`${this.api}/${this.endpoint}${id}`);
  }

}

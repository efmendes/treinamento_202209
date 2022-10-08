import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ICliente } from '../interfaces/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {
  api = environment.api;
  endpoint = 'clientes';
  constructor(private http: HttpClient) { }

  listarTodosClientes(){
    return this.http.get<ICliente[]>(`${this.api}/${this.endpoint}/`)
  }

  cadastrarEditar(cliente: ICliente){
    if(cliente.id){
      return this.http.put(`${this.api}/${this.endpoint}/${cliente.id}`, cliente);
    }
    return this.http.post(`${this.api}/${this.endpoint}/`, cliente);
  }

  remover(id: number){
    return this.http.delete(`${this.api}/${this.endpoint}/${id}`);
  }

  buscarPorId(id: string){
    return this.http.get<ICliente>(`${this.api}/${this.endpoint}/${id}`);
  }


  buscarClienteCpf(cpf: string){
    return this.http.get(`${this.api}/${this.endpoint}/buscarPorCpf/${cpf}`);
  }
}

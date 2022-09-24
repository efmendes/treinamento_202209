import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ICliente } from '../interfaces/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  endpoint = 'clientes/';
  api = environment.api;

  // let authorizationData = 'Basic ' + btoa(username + ':' + password);

  constructor(private http: HttpClient) { }

  listarTodosClientes() {
    return this.http.get<ICliente[]>(`${this.api}/${this.endpoint}`);
  }

  adcionarCliente(cliente: Partial<ICliente>){
    return this.http.post<ICliente>(`${this.api}/${this.endpoint}`, cliente);
  }

  removerCliente(id: string){
    return this.http.delete<ICliente>(`${this.api}/${this.endpoint}/${id}`);
  }

}

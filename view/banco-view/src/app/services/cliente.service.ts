import { Cliente } from './../interfaces/cliente';

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private api = `${environment.url}/clientes/`;

  constructor(private http: HttpClient) { }

  buscarTodosClientes(){
    return this.http.get<Cliente[]>(this.api);
  }
}

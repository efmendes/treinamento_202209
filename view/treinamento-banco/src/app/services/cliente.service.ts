import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ICliente } from '../interfaces/cliente';
import { IConta } from '../interfaces/conta';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  constructor(private httpService: HttpClient) { }
  status: string = "";
  errorMessage: string = "";

  buscarClientes() {
    return this.httpService.get<ICliente[]>(`${environment.url}/clientes/`);
  }

  deletarCliente(id: number) {
    this.httpService.delete(`${environment.url}/clientes/${id}`).subscribe({
      next: data => {
        this.status = 'Delete successful';
      },
      error: error => {
        this.errorMessage = error.message;
        console.error('There was an error!', error);
      }
    });
  }

  buscarContas() {
    return this.httpService.get<IConta[]>(`${environment.url}/contas`);
  }

  deletarConta(id: number) {
    this.httpService.delete(`${environment.url}/contas/${id}`);
  }
}

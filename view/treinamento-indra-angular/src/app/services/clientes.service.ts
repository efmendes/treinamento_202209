import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ICliente } from '../interfaces/cliente';
interface GetClientByCpfProps {
  cpfMascarado: string,
  observacoes: string,
  nome: string
}
@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  endpoint = 'clientes/';
  api = environment.api;

  constructor(private http: HttpClient) { }

  getAllClients() {
    return this.http.get<ICliente[]>(`${this.api}/${this.endpoint}`);
  }

  getClientById(id: number) {
    return this.http.get<ICliente>(`${this.api}/${this.endpoint}${id}`);
  }

  getClientByName(name: string) {
    return this.http.get<ICliente[]>(`${this.api}/${this.endpoint}buscarPorNome/${name}`);
  }

  getClientByCpf(cpf: string) {
    return this.http.get<GetClientByCpfProps>(`${this.api}/${this.endpoint}buscarPorCpf/${cpf}`);
  }

  createClient(client: ICliente) {
    return this.http.post<ICliente>(`${this.api}/${this.endpoint}`, client);
  }

  updateClientById(client: ICliente) {
    return this.http.put<ICliente>(`${this.api}/${this.endpoint}${client.id}`, client);

  }
  deleteClientById(id: number) {
    return this.http.delete<ICliente>(`${this.api}/${this.endpoint}${id}`);
  }
}

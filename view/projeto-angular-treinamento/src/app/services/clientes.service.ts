import { LoginService } from 'src/app/services/login.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ICliente } from '../interfaces/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  endpoint = 'clientes';
  api = environment.api;


  constructor(private http: HttpClient, private loginService: LoginService) { }

  listarTodosClientes() {
    return this.http.get<ICliente[]>(`${this.api}/${this.endpoint}/`);
  }

  adcionarCliente(cliente: Partial<ICliente>){
    return this.http.post<ICliente>(`${this.api}/${this.endpoint}`, cliente);
  }

  removerCliente(id: string){
    return this.http.delete<ICliente>(`${this.api}/${this.endpoint}/del/${id}`, this.loginService.httpOptions);
  }

 atualizarCliente(cliente: ICliente){
  return this.http.put(`${this.api}/${this.endpoint}/${cliente.id}`, cliente, this.loginService.httpOptions);
  }

  buscarClientePorId(id: number) {
    return this.http.get<ICliente>(`${this.api}/${this.endpoint}/${id}`);
  }

  buscarClientePorCpf(cpf: string){
    return this.http.get<ICliente>(`${this.api}/${this.endpoint}/buscarPorCpf/${cpf}`);
  }
}

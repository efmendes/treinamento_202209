import { LoginService } from './login.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { IConta } from '../interfaces/conta';

@Injectable({
  providedIn: 'root'
})
export class ContasService {

  endpoint = 'contas';
  api = environment.api;


  constructor(private http: HttpClient,
    private loginService: LoginService) { }

  listarTodasContas() {
    return this.http.get<IConta[]>(`${this.api}/${this.endpoint}`);
  }

  adcionarConta(cliente: Partial<IConta>){
    return this.http.post<IConta>(`${this.api}/${this.endpoint}`, cliente, this.loginService.httpOptions);
  }

  removerConta(id: string){
    return this.http.delete<IConta>(`${this.api}/${this.endpoint}/del/${id}`, this.loginService.httpOptions);
  }

  buscarClientePorId(id: number) {
    return this.http.get<IConta>(`${this.api}/${this.endpoint}/${id}`);
  }
}

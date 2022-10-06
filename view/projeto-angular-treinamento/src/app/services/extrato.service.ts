import { LoginService } from 'src/app/services/login.service';
import { LocalStorageService } from './local-storage.service';
import { Extrato } from './../interfaces/extrato';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ExtratoService {

  endpoint = 'extratos';
  api = environment.api;
  constructor(private http: HttpClient) { }

  listarTodosExtratos(cpf: string) {
    return this.http.get<Extrato[]>(`${this.api}/${this.endpoint}/all/${cpf}`);
  }

  listarExtratosPorData(cpf: string, data: string){
    return this.http.get<Extrato[]>(`${this.api}/${this.endpoint}/${cpf}/${data}`);
  }

  listarExtratosPorIntervalo(cpf: string, dataIni: string, dataFim: string){
    return this.http.get<Extrato[]>(`${this.api}/${this.endpoint}/${cpf}/${dataIni}/${dataFim}`);
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Extrato } from '../interfaces/extrato';

@Injectable({
  providedIn: 'root'
})
export class ExtratoService {

  endpoint = 'contas/extratoConta';
  api = environment.api;

  constructor(private http: HttpClient) { }

  buscarExtratoPorData(agencia: string, conta: string, dataInicial: string, dataFinal: string) {
    return this.http.get<Extrato[]>(`${this.api}/${this.endpoint}/${agencia}/${conta}/${dataInicial}/${dataFinal}`)
  }


}

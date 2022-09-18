import { ContaBancaria } from './../interfaces/conta-bancaria';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ContaBancariaService {

  private API = `${environment.url}/contas/`
  constructor(private http: HttpClient) { }

  findAll(){
      return this.http.get<ContaBancaria[]>(this.API);
  }
}

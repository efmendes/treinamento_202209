import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {IContaBancaria} from "../interfaces/conta-bancaria";

@Injectable({
  providedIn: 'root'
})
export class ContaBancariaServiceService {
  private api:string = `${environment.url}/contas/`
  constructor(private httpContas:HttpClient) { }

  buscarTodasContas():Observable<IContaBancaria[]>{
    return this.httpContas.get<IContaBancaria[]>(this.api);
  }
}

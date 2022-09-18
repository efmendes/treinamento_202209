import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ICliente} from "../interfaces/cliente";

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private api: string = `${environment.url}/clientes/`;
  constructor(private httpCLiente: HttpClient) { }

  BuscarTodosClientes():Observable<ICliente[]>{
    return this.httpCLiente.get<ICliente[]>(this.api);
  }
}

import { ICliente } from './../interfaces/cliente';
import { HttpClient} from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private api = `${environment.url}/clientes/`;

  constructor(private http: HttpClient) { }

  buscarTodosClientes(): Observable<ICliente[]> {
    return this.http.get<ICliente[]>(this.api);
  }

}

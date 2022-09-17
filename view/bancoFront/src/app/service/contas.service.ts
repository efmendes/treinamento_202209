import { IConta } from './../interfaces/conta';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ICliente } from '../interfaces/cliente';

@Injectable({
  providedIn: 'root'
})
export class ContasService {


  private api = `${environment.url}/contas/`;

  constructor(private http: HttpClient) { }

  buscarTodasContas(): Observable<IConta[]> {
    return this.http.get<IConta[]>(this.api);
  }
}

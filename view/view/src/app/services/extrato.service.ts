import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root'
})

export class extratoService {

    api = environment.api;

    endpoint = 'extrato';
    constructor(private http: HttpClient){}

    consultaExtrato(agencia: string, conta: string){
        return this.http.get(`${this.api}/${this.endpoint}/extrato/${agencia}/${conta}`);
      }
}

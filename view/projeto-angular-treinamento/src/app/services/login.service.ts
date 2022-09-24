import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Login } from '../interfaces/login';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  endpoint = '/login/';
  api = environment.api;

  username: string | undefined = '';
  password: string | undefined= '';
  authorizationData = 'Basic ' + btoa(this.username + ':' + this.password);
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': this.authorizationData
    })
  };

  setData(user: string | undefined, pass: string | undefined){

    this.username = user;
    this.password = pass;

  }

  authenticate(login: Partial<Login>){
    return this.http.post<Login>(`${this.api}${this.endpoint}authenticate`, login);
  }

  cadastro(login: Partial<Login>){
    return this.http.post<Login>(`${this.api}${this.endpoint}`, login);
  }
}

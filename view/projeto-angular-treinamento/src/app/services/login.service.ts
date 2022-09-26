import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Login } from '../interfaces/login';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  endpoint = 'login';
  api = environment.api;

  public username: string | undefined = '';
  public password: string | undefined= '';
  public authorizationData = '';
  public httpOptions = {
    headers: new HttpHeaders()
  };

  setData(login: Partial<Login>){

    this.username = login.username;
    this.password = login.password;

    this.authorizationData = 'Basic ' + btoa(this.username + ':' + this.password);
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': this.authorizationData
      })
    };

  }

  authenticate(login: Partial<Login>){
    return this.http.post<Login>(`${this.api}/${this.endpoint}/authenticate`, login);
  }

  cadastro(login: Partial<Login>){
    return this.http.post<Login>(`${this.api}/${this.endpoint}`, login);
  }
}

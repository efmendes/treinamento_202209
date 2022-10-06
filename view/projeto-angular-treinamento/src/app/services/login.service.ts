import { LocalStorageService } from './local-storage.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Login } from '../interfaces/login';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(
    private http: HttpClient,
    private localStorage: LocalStorageService
    ) { }

  endpoint = 'login';
  api = environment.api;

  public username: string | undefined = '';
  public password: string | undefined= '';
  public authorizationData = '';
  public httpOptions = {
    headers: new HttpHeaders()
  };

  setData(login: Partial<Login>){

    if(!this.localStorage.get('username')){
      this.localStorage.set('username', String(login.username));
      this.localStorage.set('password', String(login.password));
      this.localStorage.set('cpf', String(login.cliente?.cpf));
    }

    this.username = this.localStorage.get('username');
    this.password = this.localStorage.get('password');

    this.authorizationData = 'Basic ' + btoa(this.localStorage.get('username') + ':' + this.localStorage.get('password'));
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

  logOut(){
    this.localStorage.clear();
  }
}

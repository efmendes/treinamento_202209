import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { IConta } from '../interfaces/conta';

@Injectable({
  providedIn: 'root'
})
export class ContasService {

  constructor(private httpService: HttpClient) { }

  endpoint = 'contas/';
  api = environment.api;

  getAllAccounts() {
    return this.httpService.get<IConta[]>(`${this.api}/${this.endpoint}`);
  }

  getAccountById(id: number) {
    return this.httpService.get<IConta>(`${this.api}/${this.endpoint}${id}`);
  }

  createAccount(account: IConta) {
    return this.httpService.post<IConta>(`${this.api}/${this.endpoint}`, account);
  }

  updateAccountById(id: number, account: IConta) {
    return this.httpService.put<IConta>(`${this.api}/${this.endpoint}${id}`, account);
  }

  deleteAccountById(id: number) {
    return this.httpService.delete<IConta>(`${this.api}/${this.endpoint}${id}`);
  }
}
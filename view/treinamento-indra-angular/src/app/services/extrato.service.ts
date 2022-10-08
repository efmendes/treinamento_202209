import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { IExtrato } from '../interfaces/extrato';

interface DepositAndWithdrawProps {
  agencia: string,
  numeroConta: string,
  valor: number
}

interface TransferenceProps {
  agenciaDestino: string,
  agenciaOrigem: string,
  numeroContaDestino: string,
  numeroContaOrigem: string,
  valor: number
}

@Injectable({
  providedIn: 'root'
})
export class ExtratoService {

  endpointAccounts = 'contas/';
  endpointExtract = 'extratoConta/';
  api = environment.api;

  constructor(private httpService: HttpClient) { }

  depositFromAccount(options: DepositAndWithdrawProps) {
    return this.httpService.put<IExtrato[]>(`${this.api}/${this.endpointAccounts}deposito`, options);
  }

  withdrawFromAccount(options: DepositAndWithdrawProps) {
    return this.httpService.put<IExtrato[]>(`${this.api}/${this.endpointAccounts}sacar`, options);
  }

  transferenceFromAccountToAccount(options: TransferenceProps) {
    return this.httpService.put<IExtrato[]>(`${this.api}/${this.endpointAccounts}transferencia`, options);
  }

  extractByDate(agency: string, account: string, startDate: string, endDate: string) {
    return this.httpService.get<IExtrato[]>(`${this.api}/${this.endpointAccounts}${this.endpointExtract}${agency}/${account}/${startDate}/${endDate}`);
  }
}

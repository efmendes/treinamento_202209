import { ClienteContaResponse, ICliente } from "./cliente";

export interface IConta {
    id: number;
    agencia: string;
    numero: string;
    saldo: number;
    cliente?: ICliente ;
}

export interface IContaCreatedResponse {
  id?:string;
  agencia: string;
  numero: string;
  saldo: number;
  cliente: ClienteContaResponse ;
}

export interface IContaBuscaCpf{
  agencia: string,
  cpf: string,
  nomeTitular: string;
  numero: string,
  saldo: number
}

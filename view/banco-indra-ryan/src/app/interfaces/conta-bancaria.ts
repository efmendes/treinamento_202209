import {ICliente} from "./cliente";

export interface IContaBancaria {
  id:string;
  numero:string;
  saldo:number;
  agencia:string;
  cliente: ICliente;
}

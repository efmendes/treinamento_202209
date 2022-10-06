import { ICliente } from 'src/app/interfaces/cliente';
export interface Login {
  username: string;
  password: string;
  cliente?: ICliente
}

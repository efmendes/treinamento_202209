export interface ICliente {
  id?: number;
  cpf: string;
  email: string;
  nome: string;
  ativo?: boolean;
  observacoes?: string;
}

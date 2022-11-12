export interface ICliente {
  id?: number;
  cpf: string;
  email: string;
  nome: string;
  observacoes?: string;
  ativo?: boolean;
}

export interface ICliente {
  id: string;
  cpf: string;
  email: string;
  nome: string;
  observacoes?: string;
  ativo?: boolean;
}

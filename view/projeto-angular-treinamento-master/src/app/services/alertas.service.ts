import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AlertasService {

  constructor() { }

  alertaSucesso(mensagem: string) {
    Swal.fire({
      title: 'PARABÉNS!',
      text: mensagem,
      icon: 'success'
    });
  }

  alertaErro(mensagem: string) {
    Swal.fire({
      title: 'OCORREU UM ERRO!',
      text: mensagem,
      icon: 'error'
    });
  }

  alertaTransacaoSucesso(mensagem: string) {
    Swal.fire({
      title: 'OPERAÇÃO APROVADA!',
      text: mensagem,
      icon: 'success'
    });
  }

  alertaDeletar(mensagem: string) {
    Swal.fire({
      title: 'Tem certeza que deseja DELETAR?',
      text: 'Essa ação excluirá a conta!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire(
          'Deleted!',
          mensagem,
          'success'
        )
      }
    })
  }
}

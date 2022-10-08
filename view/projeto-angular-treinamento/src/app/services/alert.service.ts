import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  constructor() { }

  alertaSucesso(mensagem: string) {
    Swal.fire({
      title: 'Sucesso',
      text: mensagem,
      icon: 'success'
    });
  }
  alertaLogado(mensagem: string) {
    Swal.fire({
      title: 'Logado',
      text: mensagem,
      icon: 'success'
    });
  }

  alertaErro(mensagem: string) {
    Swal.fire({
      title: 'Erro!',
      text: mensagem,
      icon: 'error'
    });
  }
}


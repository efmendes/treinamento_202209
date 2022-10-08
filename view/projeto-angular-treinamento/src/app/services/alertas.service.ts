import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root',
})
export class AlertasService {
  constructor() {}

  alertaLoading(mensagem: string) {
    Swal.fire({
      title: mensagem,
      html: 'Aguarde um instante',
      showConfirmButton: false,
    });
    Swal.showLoading();
  }

  alertaSucess(mensagem: string, afterClosing = () => {}) {
    Swal.fire({
      title: 'Sucesso!',
      text: mensagem,
      icon: 'success',
      confirmButtonColor: '#004E89',
    }).then(() => afterClosing());
  }

  alertaFalha(mensagem: string, afterClosing = () => {}) {
    Swal.fire({
      title: 'Ops...',
      text: mensagem,
      icon: 'error',
      confirmButtonColor: '#004E89',
    }).then(() => afterClosing());
  }
}

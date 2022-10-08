import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AlertaService {

  constructor() { }

  successAlert(message: string) {
    Swal.fire({
      title: "Sucesso!",
      text: message,
      icon: 'success',
    })
  }

  errorAlert(message: string) {
    Swal.fire({
      title: "Error!",
      text: message,
      icon: 'error',
    })
  }

  warningAlert(message: string) {
    Swal.fire({
      title: "Wait!",
      text: message,
      icon: 'warning',
    })
  }
}

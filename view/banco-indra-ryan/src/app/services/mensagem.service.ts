import { Injectable } from '@angular/core';
import {MatSnackBar, MatSnackBarConfig} from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class MensagemService {

  constructor(private snackBar: MatSnackBar) {

  }
  error(mensagem: string): void {
    this.abrirSnackBar(mensagem, ['error']);
  }

  warning(mensagem: string): void {
    this.abrirSnackBar(mensagem, ['warning']);
  }

  info(mensagem: string): void {
    this.abrirSnackBar(mensagem, ['info']);
  }

  success(mensagem: string): void {
    this.abrirSnackBar(mensagem, ['success']);
  }

  private abrirSnackBar(mensagem: string, extraClasses: string[]): void {
    const config = new MatSnackBarConfig();
    config.duration = 10000;
    config.politeness = 'assertive';
    config.panelClass = extraClasses;
    this.snackBar.open(mensagem, 'X', config);
  }
}

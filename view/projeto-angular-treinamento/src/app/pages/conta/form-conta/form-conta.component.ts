import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { IConta } from 'src/app/interfaces/conta';
import { ClientesService } from 'src/app/services/clientes.service';
import { ContasService } from 'src/app/services/contas.service';

@Component({
  selector: 'app-form-conta',
  templateUrl: './form-conta.component.html',
  styleUrls: ['./form-conta.component.css']
})
export class FormContaComponent implements OnInit {

  constructor(
    private contaService: ContasService,
    private location: Location,
    private route: ActivatedRoute) {}

  idConta = 0;


  contaForm = new FormGroup({
      agencia: new FormControl('', Validators.required),
      numero: new FormControl('', Validators.required)
    });


  ngOnInit(): void {
    this.idConta = Number(this.route.snapshot.paramMap.get('id'));

    this.contaService.buscarContaPorId(this.idConta).subscribe((conta: IConta) => {
      this.contaForm.setValue({
        agencia: conta.agencia,
        numero: conta.numero
      });
    }, (error) => {
      console.error(error);
    });
  }


  cadastrar() {
    const contaValue = this.contaForm.value as IConta;
    this.contaService.buscarContaPorId(this.idConta).subscribe(
      (conta: IConta) => {
        contaValue.cliente = conta.cliente
        contaValue.id = conta.id
        contaValue.saldo = conta.saldo
        this.contaService.atualizarConta(contaValue).subscribe();
      }
    )

    this.onBack();
  }


  onBack(){
    this.location.back();
  }
}

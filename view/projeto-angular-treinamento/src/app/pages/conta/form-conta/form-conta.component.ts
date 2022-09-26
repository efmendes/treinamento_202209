import { IConta } from './../../../interfaces/conta';
import { ContasService } from 'src/app/services/contas.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-form-conta',
  templateUrl: './form-conta.component.html',
  styleUrls: ['./form-conta.component.css']
})
export class FormContaComponent implements OnInit {

  constructor(
    private service: ContasService,
    private location: Location,
    private route: ActivatedRoute) { }

    idConta = 0;

    clienteForm = new FormGroup({
      nome: new FormControl('', Validators.required),
      cpf: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      observacoes: new FormControl(''),
      ativo: new FormControl(true)
    });

    ngOnInit(): void {
      this.idConta = Number(this.route.snapshot.paramMap.get('id'));
      if (this.idConta !== 0) {
        this.service.buscarClientePorId(this.idConta).subscribe((conta: IConta) => {
          this.clienteForm.setValue({
            nome: cliente.nome,
            cpf: cliente.cpf,
            email: cliente.email,
            observacoes: cliente.observacoes || '',
            ativo: cliente.ativo || false
          });
        }, (error) => {
          console.error(error);
        });
      }
    }

    cadastrar() {
      const conta: IConta = this.clienteForm.value as ICliente;
      if (this.idCliente) {
        cliente.id = String(this.idCliente);
        this.service.atualizarCLiente(cliente).subscribe();
        return;
      }

      cliente.ativo = true;
      this.service.adcionarCliente(cliente).subscribe(() => {
        alert('SUCESSO!!!!!!');
      }, (error) => {
        console.error(error);
      });
      this.onBack();
    }


  onBack(){
    this.location.back();
  }
}

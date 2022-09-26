import { ActivatedRoute } from '@angular/router';
import { ICliente } from './../../../interfaces/cliente';
import { Location } from '@angular/common';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { ClientesService } from 'src/app/services/clientes.service';

@Component({
  selector: 'app-form-client',
  templateUrl: './form-client.component.html',
  styleUrls: ['./form-client.component.css']
})
export class FormClientComponent implements OnInit {


  constructor(private formBuilder: NonNullableFormBuilder,
    private service: ClientesService,
    private location: Location,
    private route: ActivatedRoute) { }

    idCliente = 0;

    clienteForm = new FormGroup({
      nome: new FormControl('', Validators.required),
      cpf: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      observacoes: new FormControl(''),
      ativo: new FormControl(true)
    });

    ngOnInit(): void {
      this.idCliente = Number(this.route.snapshot.paramMap.get('id'));
      if (this.idCliente !== 0) {
        this.service.buscarClientePorId(this.idCliente).subscribe((cliente: ICliente) => {
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
      const cliente: ICliente = this.clienteForm.value as ICliente;
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

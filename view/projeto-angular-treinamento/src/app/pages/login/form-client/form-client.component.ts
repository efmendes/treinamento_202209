import { ActivatedRoute, Router } from '@angular/router';
import { ICliente } from '../../../interfaces/cliente';
import { Location } from '@angular/common';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { ClientesService } from 'src/app/services/clientes.service';
import { AlertService } from 'src/app/services/alert.service';

@Component({
  selector: 'app-form-client',
  templateUrl: './form-client.component.html',
  styleUrls: ['./form-client.component.css']
})
export class FormClientComponent implements OnInit {


  constructor(private formBuilder: NonNullableFormBuilder,
    private service: ClientesService,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private alert: AlertService) { }



    clienteForm = new FormGroup({
      nome: new FormControl('', Validators.required),
      cpf: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      observacoes: new FormControl(''),
      ativo: new FormControl(true)
    });

    ngOnInit(): void {

    }

    cadastrar() {
      const cliente: ICliente = this.clienteForm.value as ICliente
      cliente.ativo = true;
      this.service.adcionarCliente(cliente).subscribe((cliente: ICliente) => {
        if(cliente){
          this.alert.alertaSucesso('Cliente cadastrado com sucesso!');
          this.router.navigate([`/login/cadastrar/${cliente.cpf}`]);
        }
      }, (error: Error) => {
        this.alert.alertaErro('Falha na criação do Cliente!')
      });


    }


  onBack(){
    this.location.back();
  }


}

import { ActivatedRoute, Router } from '@angular/router';
import { ICliente } from '../../../interfaces/cliente';
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
    private router: Router,
    private route: ActivatedRoute) { }



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

        this.router.navigate([`/login/cadastrar/${cliente.cpf}`]);

      }, (error) => {
        console.error(error);
      });


    }


  onBack(){
    this.location.back();
  }


}

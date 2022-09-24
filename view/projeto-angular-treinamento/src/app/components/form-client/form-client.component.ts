import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import {  NonNullableFormBuilder } from '@angular/forms';
import { ClientesService } from 'src/app/services/clientes.service';

@Component({
  selector: 'app-form-client',
  templateUrl: './form-client.component.html',
  styleUrls: ['./form-client.component.css']
})
export class FormClientComponent implements OnInit {

  @Output() close = new EventEmitter();

  constructor(private formBuilder: NonNullableFormBuilder,
    private service: ClientesService) { }

  ngOnInit(): void {
  }

  displayStyle: string = "block";

  form = this.formBuilder.group({
    nome: [''],
    cpf: [''],
    email: [''],
    observacoes: ['']
  });

  onSubmit() {
    this.service.adcionarCliente(this.form.value).subscribe();
    this.closePopup();
  }

  closePopup(){
    this.displayStyle = 'none';
    this.close.emit(false);
  }




}

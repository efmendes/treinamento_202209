import { ClientesService } from 'src/app/services/clientes.service';
import { NonNullableFormBuilder } from '@angular/forms';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-form-update-client',
  templateUrl: './form-update-client.component.html',
  styleUrls: ['./form-update-client.component.css']
})
export class FormUpdateClientComponent implements OnInit {

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

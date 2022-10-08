import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-new-user-form',
  templateUrl: './new-user-form.component.html',
  styleUrls: ['./new-user-form.component.css'],
})
export class NewUserFormComponent implements OnInit {
  @Input() onSubmitForm: (formValues: FormGroup) => void = () => {};

  newUser = new FormGroup({
    name: new FormControl(''),
    mail: new FormControl(''),
    cpf: new FormControl(''),
    observations: new FormControl(''),
  });

  constructor() {}

  ngOnInit(): void {}

  onSubmit() {
    // TODO: Use EventEmitter with form value
    console.warn(this.newUser);
    this.onSubmitForm(this.newUser);
  }
}

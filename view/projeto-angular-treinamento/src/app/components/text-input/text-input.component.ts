import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-text-input',
  templateUrl: './text-input.component.html',
  styleUrls: ['./text-input.component.css'],
})
export class TextInputComponent implements OnInit {
  @Input() label: string = '';
  @Input() placeholder: string = 'Digite aqui';

  constructor() {}

  ngOnInit(): void {}
}

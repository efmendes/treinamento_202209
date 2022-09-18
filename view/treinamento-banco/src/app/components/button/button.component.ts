import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent implements OnInit {

  constructor() { }
  buttonMsg = "Me clique";

  @Input()
  variation: "dark" | "light" | "primary" | "success" | "danger" = "dark";

  @Input()
  text: string = this.buttonMsg;

  ngOnInit(): void {
  }
}
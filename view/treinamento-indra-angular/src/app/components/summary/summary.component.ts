import { Component, Input, OnInit } from '@angular/core';
import { faBuildingColumns, faUsers, faDollar } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css']
})
export class SummaryComponent implements OnInit {

  @Input() page: string;

  bank = faBuildingColumns;
  clients = faUsers;
  dollar = faDollar;

  constructor() { }

  ngOnInit(): void {
  }

}

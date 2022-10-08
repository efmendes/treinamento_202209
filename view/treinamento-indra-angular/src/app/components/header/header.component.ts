import { Component, Input, OnInit } from '@angular/core';
import { DataService } from 'src/app/services/data.service';
import { NavigationEnd, Router } from '@angular/router';
import { delay, filter } from 'rxjs';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Input() page: string;

  constructor(
    private dataService: DataService
  ) { }

  ngOnInit(): void {
  }


  create() {
    this.dataService.sendCreateButtonClick();
  }
}

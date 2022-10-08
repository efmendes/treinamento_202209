import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  subject = new Subject();
  constructor() { }

  sendCreateButtonClick() {
    this.subject.next("");
  }

  clickCreate(): Observable<any> {
    return this.subject.asObservable();
  }

}

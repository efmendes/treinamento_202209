import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { delay, filter } from 'rxjs';
import { faArrowUp } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'projeto-angular';

  pointUpIcon = faArrowUp;

  page: string;

  constructor(
    private route: Router
  ) { }

  ngOnInit(): void {
    this.page = String(this.route.url);
    this.route.events
      .pipe(
        delay(10),
        filter((e) => e instanceof NavigationEnd),
      )
      .subscribe((event: any) => {
        this.page = event.url;
      });
  }

  scrollToTop(): void {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }
}

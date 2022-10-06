import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExtratosComponent } from './extratos.component';

describe('ExtratosComponent', () => {
  let component: ExtratosComponent;
  let fixture: ComponentFixture<ExtratosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExtratosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExtratosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

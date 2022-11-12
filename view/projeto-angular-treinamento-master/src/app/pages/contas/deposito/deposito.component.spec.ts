import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepositoComponent } from './deposito.component';

describe('DepositoComponent', () => {
  let component: DepositoComponent;
  let fixture: ComponentFixture<DepositoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DepositoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DepositoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

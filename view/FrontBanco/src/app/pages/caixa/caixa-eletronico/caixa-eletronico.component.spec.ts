import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaixaEletronicoComponent } from './caixa-eletronico.component';

describe('CaixaEletronicoComponent', () => {
  let component: CaixaEletronicoComponent;
  let fixture: ComponentFixture<CaixaEletronicoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CaixaEletronicoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CaixaEletronicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

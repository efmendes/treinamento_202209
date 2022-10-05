import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastraEditaClienteComponent } from './cadastra-edita-cliente.component';

describe('CadastraEditaClienteComponent', () => {
  let component: CadastraEditaClienteComponent;
  let fixture: ComponentFixture<CadastraEditaClienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CadastraEditaClienteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastraEditaClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

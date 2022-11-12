import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClienteContasComponent } from './cliente-contas.component';

describe('ClienteContasComponent', () => {
  let component: ClienteContasComponent;
  let fixture: ComponentFixture<ClienteContasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClienteContasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClienteContasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

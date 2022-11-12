import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarContaComponent } from './editar-conta.component';

describe('EditarContaComponent', () => {
  let component: EditarContaComponent;
  let fixture: ComponentFixture<EditarContaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditarContaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarContaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

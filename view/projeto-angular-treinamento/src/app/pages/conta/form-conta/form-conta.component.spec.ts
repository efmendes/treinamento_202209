import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormContaComponent } from './form-conta.component';

describe('FormContaComponent', () => {
  let component: FormContaComponent;
  let fixture: ComponentFixture<FormContaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormContaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormContaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

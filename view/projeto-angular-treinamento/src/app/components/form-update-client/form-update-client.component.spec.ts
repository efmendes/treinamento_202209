import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormUpdateClientComponent } from './form-update-client.component';

describe('FormUpdateClientComponent', () => {
  let component: FormUpdateClientComponent;
  let fixture: ComponentFixture<FormUpdateClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormUpdateClientComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormUpdateClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

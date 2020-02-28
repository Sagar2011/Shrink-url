import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginDialogBoxComponent } from './login-dialog-box.component';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';

describe('LoginDialogBoxComponent', () => {
  let component: LoginDialogBoxComponent;
  let fixture: ComponentFixture<LoginDialogBoxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginDialogBoxComponent ],
      imports: [
        MatIconModule,
        MatDialogModule
      ],
      providers: [
        {
          provide: MatDialogRef,
          useValue: {}
        }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginDialogBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

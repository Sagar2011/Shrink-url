import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LinkDashboardComponent } from './link-dashboard.component';

describe('LinkDashboardComponent', () => {
  let component: LinkDashboardComponent;
  let fixture: ComponentFixture<LinkDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LinkDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LinkDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

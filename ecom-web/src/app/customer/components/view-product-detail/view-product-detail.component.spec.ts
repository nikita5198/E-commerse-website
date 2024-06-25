import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewProductDetailComponent } from './view-product-detail.component';

describe('ViewProductDetailComponent', () => {
  let component: ViewProductDetailComponent;
  let fixture: ComponentFixture<ViewProductDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewProductDetailComponent]
    });
    fixture = TestBed.createComponent(ViewProductDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

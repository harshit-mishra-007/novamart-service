import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute, convertToParamMap, provideRouter } from '@angular/router';
import { of } from 'rxjs';
import { ProductService } from '../../services/product.service';
import { ProductListComponent } from './product-list.component';

describe('ProductListComponent', () => {
  let fixture: ComponentFixture<ProductListComponent>;
  let component: ProductListComponent;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductListComponent],
      providers: [
        provideRouter([]),
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
        {
          provide: ProductService,
          useValue: {
            getProducts: () =>
              of([
                {
                  id: '1',
                  productId: 'P-1',
                  name: 'Test Product',
                  description: 'Test Description',
                  price: 1,
                  category: 'Test Category',
                  stock: 1,
                  active: true,
                },
              ]),
          },
        },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(ProductListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
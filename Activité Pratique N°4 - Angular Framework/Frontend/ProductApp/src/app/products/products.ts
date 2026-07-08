import { Component, OnInit, signal } from '@angular/core';
import { ProductService } from '../services/product';

@Component({
  selector: 'app-products',
  imports: [],
  templateUrl: './products.html',
  styleUrl: './products.css',
  standalone: true,
})
export class Products implements OnInit {
  products = signal<any[]>([]);

  constructor(private productService: ProductService) {}

  ngOnInit() {
    this.getAllProducts();
  }

  getAllProducts() {
    this.productService.getAllProducts().subscribe({
      next: (resp) => {
        this.products.set(resp as any[]);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  HandleDelete(product: any) {
    const v = confirm(`Deleting product ${product.name}`);

    if (v) {
      this.productService.deleteProducts(product).subscribe({
        next: () => {
          this.getAllProducts();
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
  }
}

import { Component, OnInit, Input } from '@angular/core';
import { ShoppingCartService } from '@shared/services/shopping-cart.service';
import { ShoppingCart } from '@shared/models/shopping-cart';

@Component({
  selector: 'app-shopping-cart-summary',
  templateUrl: './shopping-cart-summary.component.html',
  styleUrls: ['./shopping-cart-summary.component.css']
})
export class ShoppingCartSummaryComponent {
  @Input() cart: ShoppingCart;
}

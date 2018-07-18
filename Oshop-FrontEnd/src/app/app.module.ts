import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AdminAuthGuardService } from '@shared/services/admin-auth-guard.service';
import { TokenInterceptorService } from '@shared/services/token-interceptor.service';
import { SharedModule } from '@shared/shared.module';
import { FlashMessagesModule } from 'angular2-flash-messages';
import { DataTableModule } from 'angular5-data-table';
import { CustomFormsModule } from 'ng2-validation';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminOrdersComponent } from './components/admin/admin-orders/admin-orders.component';
import { AdminProductsComponent } from './components/admin/admin-products/admin-products.component';
import { ProductFormComponent } from './components/admin/product-form/product-form.component';
import { CheckOutComponent } from './components/check-out/check-out.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { MyOrdersComponent } from './components/my-orders/my-orders.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { OrderDetailsComponent } from './components/order-details/order-details.component';
import { OrderSuccessComponent } from './components/order-success/order-success.component';
import { ProductFilterComponent } from './components/products/product-filter/product-filter.component';
import { ProductsComponent } from './components/products/products.component';
import { RegisterComponent } from './components/register/register.component';
import { ShippingFormComponent } from './components/shipping-form/shipping-form.component';
import { ShoppingCartSummaryComponent } from './components/shopping-cart-summary/shopping-cart-summary.component';
import { ShoppingCartComponent } from './components/shopping-cart/shopping-cart.component';
import { EmailValidatorDirective } from './directives/email-validator.directive';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    ProductsComponent,
    ShoppingCartComponent,
    CheckOutComponent,
    OrderSuccessComponent,
    LoginComponent,
    MyOrdersComponent,
    AdminProductsComponent,
    AdminOrdersComponent,
    RegisterComponent,
    EmailValidatorDirective,
    ProductFormComponent,
    ProductFilterComponent,
    ShoppingCartSummaryComponent,
    ShippingFormComponent,
    OrderDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    CustomFormsModule,
    HttpClientModule,
    NgbModule.forRoot(),
    FlashMessagesModule.forRoot(),
    DataTableModule.forRoot(),
    SharedModule
  ],
  providers: [
    AdminAuthGuardService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

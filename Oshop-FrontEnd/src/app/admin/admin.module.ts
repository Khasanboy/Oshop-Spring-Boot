import { AdminOrdersComponent } from '@admin/components/admin-orders/admin-orders.component';
import { AdminProductsComponent } from '@admin/components/admin-products/admin-products.component';
import { ProductFormComponent } from '@admin/components/product-form/product-form.component';
import { AdminAuthGuardService } from '@admin/services/admin-auth-guard.service';
import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { DataTableModule } from '../../../node_modules/angular5-data-table';

@NgModule({
  imports: [
    SharedModule,
    DataTableModule.forRoot()
  ],
  declarations: [
    AdminProductsComponent,
    AdminOrdersComponent,
    ProductFormComponent,
  ],
  providers: [
    AdminAuthGuardService,
  ]
})
export class AdminModule { }

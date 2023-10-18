import { NgModule, ViewChildren } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { CartViewComponent } from './cart-view/cart-view.component';
import { CustomerViewComponent } from './customer-view/customer-view.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { AuthenticationGuardService } from './service/authentication-guard-service';
import { VendorAddComponent } from './vendor-add/vendor-add.component';
import { VendorReportComponent } from './vendor-report/vendor-report.component';
import { VendorViewComponent } from './vendor-view/vendor-view.component';
import { SignupPageComponent } from './signup-page/signup-page.component';

const routes: Routes = [{
  path: '',
  component: AppComponent,

  children: [
    {
      path: '',
      component: LandingPageComponent
    },
    {
      path: 'login',
      component: LoginPageComponent
    },
    {
      path: 'signup',
      component: SignupPageComponent,
      
    },
    {
      path : 'customer',
      component : CustomerViewComponent,
      canActivate: [AuthenticationGuardService]
    },
    {
      path : 'vendor',
      component : VendorViewComponent,
      canActivate: [AuthenticationGuardService]
    },
    {
      path : 'vendor/add',
      component : VendorAddComponent,
      canActivate: [AuthenticationGuardService]
    },
    {
      path : 'vendor/sales',
      component : VendorReportComponent,
      canActivate: [AuthenticationGuardService]
    },
    {
      path : '**',
      component : LandingPageComponent,
    },
  ]
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

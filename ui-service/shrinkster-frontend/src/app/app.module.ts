import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginDialogBoxComponent } from './login-dialog-box/login-dialog-box.component';
import { AuthInterceptorService } from './auth-interceptor.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DemoMaterialModule } from './material-module';
import { LinkDashboardComponent } from './link-dashboard/link-dashboard.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import {CookieService} from 'ngx-cookie-service';
import { NavbarComponent } from './navbar/navbar.component';
import { HomePageComponent } from './home-page/home-page.component';
import {FlexLayoutModule} from '@angular/flex-layout';
import { GraphComponent } from './graph/graph.component';
import { HistoryComponent } from './history/history.component';
import { InternalErrorComponent } from './internal-error/internal-error.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginDialogBoxComponent,
    LinkDashboardComponent,
    AdminDashboardComponent,
    NavbarComponent,
    HomePageComponent,
    GraphComponent,
    HistoryComponent,
    InternalErrorComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    BrowserAnimationsModule,
    DemoMaterialModule,
    FlexLayoutModule
    ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true
    },
  DemoMaterialModule,
  CookieService],
  bootstrap: [AppComponent],
  entryComponents: [LoginDialogBoxComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LinkDashboardComponent } from './link-dashboard/link-dashboard.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { HomePageComponent } from './home-page/home-page.component';


const routes: Routes = [
  {path:'dashboard', component: LinkDashboardComponent},
  {
    path:'admin', component: AdminDashboardComponent
  },
  {path:'', component: HomePageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }

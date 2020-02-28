import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LinkDashboardComponent } from './link-dashboard/link-dashboard.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { HomePageComponent } from './home-page/home-page.component';
import { GraphComponent } from './graph/graph.component';
import { HistoryComponent } from './history/history.component';
import { InternalErrorComponent } from './internal-error/internal-error.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';


const routes: Routes = [
  {path:'dashboard', component: LinkDashboardComponent},
  {
    path:'admin', component: AdminDashboardComponent
  },
  {path:'', component: HomePageComponent},
  {path:'graph', component: GraphComponent},
  {path:'history', component: HistoryComponent},
  {path:'internal', component: InternalErrorComponent},
  {path:'**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }

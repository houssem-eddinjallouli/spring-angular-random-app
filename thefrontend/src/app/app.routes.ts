import { Routes } from '@angular/router';
import { SigninComponent } from './components/signin/signin.component';
import { SignupComponent } from './components/signup/signup.component';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { UserComponent } from './components/user/user.component';
import { authGuard } from './guards/auth.guard';
import { AdminComponent } from './components/admin/admin.component';
import { UserHomeComponent } from './components/user/user-home/user-home.component';
import { AdminHomeComponent } from './components/admin/admin-home/admin-home.component';

export const routes: Routes = [
  {
    path: 'user',
    canActivate: [authGuard],
    component: UserComponent,
    children: [
      { path: '', redirectTo: '/user/home', pathMatch: 'full' },
      { path: 'home', component: UserHomeComponent },
    ],
  },

  {
    path: 'admin',
    canActivate: [authGuard],
    component: AdminComponent,
    children: [
      { path: '', redirectTo: '/admin/home', pathMatch: 'full' },
      { path: 'home', component: AdminHomeComponent },
    ],
  },

  { path: 'signin', component: SigninComponent },
  { path: 'signup', component: SignupComponent },

  { path: '', redirectTo: '/signin', pathMatch: 'full' },
  { path: '**', component: NotfoundComponent },
];

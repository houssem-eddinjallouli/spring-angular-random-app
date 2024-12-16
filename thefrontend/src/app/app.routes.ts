import { Routes } from '@angular/router';
import { SigninComponent } from './components/signin/signin.component';
import { SignupComponent } from './components/signup/signup.component';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { UserComponent } from './components/user/user.component';

export const routes: Routes = [
  { path: 'user', component: UserComponent },

  { path: 'signin', component: SigninComponent },
  { path: 'signup', component: SignupComponent },

  { path: '', redirectTo: '/signin', pathMatch: 'full' },
  { path: '**', component: NotfoundComponent },
];

import { Routes } from '@angular/router';
import { SigninComponent } from './components/signin/signin.component';
import { SignupComponent } from './components/signup/signup.component';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { UserComponent } from './components/user/user.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: 'user', canActivate: [authGuard], component: UserComponent },

  { path: 'signin', component: SigninComponent },
  { path: 'signup', component: SignupComponent },

  { path: '', redirectTo: '/signin', pathMatch: 'full' },
  { path: '**', component: NotfoundComponent },
];

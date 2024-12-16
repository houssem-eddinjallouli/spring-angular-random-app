import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const userAuthService = inject(AuthService);
  const router = inject(Router);
  
  const isLoggedIn = userAuthService.isLoggedIn();

  if (!isLoggedIn) {
    router.navigate(['signin']);
    return false;
  }

  return true;
};

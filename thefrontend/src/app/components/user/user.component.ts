import { Component, OnInit } from '@angular/core';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { Router, RouterOutlet } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-user',
  imports: [NavbarComponent, RouterOutlet],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent implements OnInit{

  constructor(private authService: AuthService, private router: Router) {}
  
  ngOnInit(): void {
    if (this.authService.getRoles().includes('ROLE_ADMIN')) {
      this.router.navigate(['/admin']);
    }
  }
}

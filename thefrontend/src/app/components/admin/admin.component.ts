import { Component, OnInit } from '@angular/core';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { AuthService } from '../../services/auth.service';
import { Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-admin',
  imports: [SidebarComponent, RouterOutlet],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent implements OnInit{

constructor(private authService: AuthService, private router: Router) {}

ngOnInit(): void {
  if (!this.authService.getRoles().includes('ROLE_ADMIN')) {
    this.router.navigate(['/user']);
  }
}

}

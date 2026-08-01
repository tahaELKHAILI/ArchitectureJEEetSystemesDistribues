import { Component } from '@angular/core';
import { AuthService } from '../../services/auth-service';

@Component({
  selector: 'app-home-screen-component',
  imports: [],
  templateUrl: './home-screen-component.html',
  styleUrl: './home-screen-component.css',
})
export class HomeScreenComponent {
  constructor(public authService:AuthService) {
  }
}

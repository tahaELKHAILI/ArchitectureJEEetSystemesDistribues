import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar-component/navbar-component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-admin-template-component',
  imports: [NavbarComponent, RouterOutlet],
  templateUrl: './app-template-component.html',
  styleUrl: './app-template-component.css',
})
export class AppTemplateComponent {}

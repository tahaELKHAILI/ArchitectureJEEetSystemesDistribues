import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar-component/navbar-component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-admin-template-component',
  imports: [NavbarComponent, RouterOutlet],
  templateUrl: './admin-template-component.html',
  styleUrl: './admin-template-component.css',
})
export class AdminTemplateComponent {}

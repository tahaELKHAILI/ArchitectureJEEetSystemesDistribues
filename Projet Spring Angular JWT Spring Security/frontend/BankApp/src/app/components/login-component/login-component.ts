import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.html',
  styleUrl: './login-component.css',
  standalone: true,
  imports: [ReactiveFormsModule],
})
export class LoginComponent implements OnInit {
  formLogin!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router:Router
  ) {}

  ngOnInit() {
    this.formLogin = this.fb.group({
      username: this.fb.control('', [Validators.required]),
      password: this.fb.control('', [Validators.required]),
    });
  }

  handleLogin() {
    let username = this.formLogin.value.username;
    let password = this.formLogin.value.password;
    this.authService.login(username, password).subscribe({
      next: (resp) => {
        this.authService.loadProfile(resp)
        this.router.navigateByUrl("/app/home")
      },
      error: (err) => {
        if(!this.formLogin.valid){
        alert("Make sure to enter a username and password")
        }
        else{
          if (err.status == 401) {
            alert('Wrong username or password.');
          } else if (err.status == 0) {
            alert('Server down. Try again later');
          }
          console.error(err);
        }
      },
    });
  }
}

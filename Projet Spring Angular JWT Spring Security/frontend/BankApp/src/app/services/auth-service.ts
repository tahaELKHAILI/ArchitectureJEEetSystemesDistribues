import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { AthenticationState } from '../models/athentication-state';
import { jwtDecode } from 'jwt-decode';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  authenticationState: AthenticationState = {
    isAthenticated: false,
    roles: [],
    username: '',
    accessToken: '',
  };

  constructor(private http: HttpClient,
              private router:Router) {
    const storedState = localStorage.getItem('authState');
    if (storedState) {
      this.authenticationState = JSON.parse(storedState);
    }
  }

  public login(username: string, password: string) {
    let headers = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded'),
    };

    let params = new HttpParams().set('username', username).set('password', password);

    return this.http.post(environment.backendHost + '/auth/login', params, headers);
  }

  loadProfile(resp: any) {
    this.authenticationState.isAthenticated = true;
    this.authenticationState.accessToken = resp['access-token'];

    const decodedJwt: any = jwtDecode(this.authenticationState.accessToken);
    this.authenticationState.username = decodedJwt.sub;
    this.authenticationState.roles = decodedJwt.scope;

    // Save authentication state
    localStorage.setItem('authState', JSON.stringify(this.authenticationState));
  }

  logout() {
    this.authenticationState = {
      isAthenticated: false,
      roles: [],
      username: '',
      accessToken: '',
    };

    localStorage.removeItem('authState');
    this.router.navigateByUrl('/login');

  }
}

import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../services/auth-service';
import { Observable } from 'rxjs';

@Injectable()
export class AppHttpInterceptor implements HttpInterceptor {
  constructor(private autService: AuthService) {
  }

  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    if(!req.url.includes("/auth/login")){
      let newRequest = req.clone({
        headers: req.headers.set(
          'Authorization',
          'Bearer ' + this.autService.authenticationState.accessToken,
        ),
      });
      return next.handle(newRequest);
    }
    else{
      return next.handle(req)
    }
  }
}

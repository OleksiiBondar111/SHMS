import {Injectable} from "@angular/core";
import {AuthService} from "../../../services/auth.service";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {login, loginFailure, loginSuccess, logout} from "../actions/auth.actions";
import {mergeMap, tap} from "rxjs/operators";
import {Router} from "@angular/router";
import {AutoLogoutService} from "../../../services/auto-logout.service";

@Injectable()
export class AuthEffects {
  constructor(private actions$: Actions,
              private authService: AuthService,
                  private autoLogoutService: AutoLogoutService,
              private router: Router) {
  }

// mergeMap vs switchMap ???
  login$ = createEffect(() =>
    this.actions$.pipe(
      ofType(login),
      mergeMap(({username, password}) =>
        // this.authService.login(username, password).pipe(
        //   map((authObj) => loginSuccess({token: authObj.access_token}),
        //     catchError((error) => of(loginFailure({error})))
        //   )
        this.authService.login(username, password)
          .then((authObj) => loginSuccess({token: authObj.access_token}))
          .catch((error) => loginFailure({error}))
      )
    ));

  loginSuccess$ = createEffect(
    () =>
      this.actions$.pipe(
        ofType(loginSuccess),
        tap(() => {
          // this.autoLogoutService.startMonitoring();
          this.router.navigate(['/dashboard']); // Navigate to the dashboard on successful login
        })
      ),
    {dispatch: false}
  );

    logOut$ = createEffect(
    () =>
      this.actions$.pipe(
        ofType(logout),
        tap(() => {
          this.router.navigate(['/login']); // Navigate to the dashboard on successful login
        })
      ),
    {dispatch: false}
  );
}

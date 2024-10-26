import {createReducer, on} from "@ngrx/store";
import {loginSuccess, logout} from "../actions/auth.actions";

export interface AuthState {
  token: string | null;
}

export const initialAuthState: AuthState = {
  token: null,
}

export const authReducer = createReducer(
  initialAuthState,
  on(loginSuccess, (state, {token}) => ({...state, token})),
  on(logout, (state) => ({...state, token: null}))
);

import { Component, OnInit } from '@angular/core';
import {logout} from "../../auth/store/actions/auth.actions";
import {Store} from "@ngrx/store";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private store: Store) { }

  ngOnInit(): void {
  }


  onLogout() {
    this.store.dispatch(logout());
    // logout();
  }

}
